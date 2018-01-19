package com.kar.githubsearch.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kar.githubsearch.R;
import com.kar.githubsearch.data.model.GithubSearchUserResponse;
import com.kar.githubsearch.ui.user_profile.UserProfileActivity;
import com.kar.githubsearch.util.EndlessRecyclerViewScrollListener;
import com.kar.githubsearch.util.Response;
import com.mancj.materialsearchbar.MaterialSearchBar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.HttpException;

import static com.kar.githubsearch.util.Response.ERROR;
import static com.kar.githubsearch.util.Response.LOADING;
import static com.kar.githubsearch.util.Response.SUCCESS;
import static com.kar.githubsearch.util.Response.UPDATE;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private MaterialSearchBar mSearchBar;
    private ProgressBar mProgressBar;
    private RecyclerView mRvUser;
    private MainActivityAdapter mAdapter;
    private EndlessRecyclerViewScrollListener mScrollListener;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityViewModel.class);
        mViewModel.response().observe(this, response -> processResponse(response));

        searchBarSetup();
        recyclerViewSetup();
    }

    private void searchBarSetup() {
        mSearchBar = findViewById(R.id.search_bar_user);
        mSearchBar.hideSuggestionsList();
        mSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchConfirmed(CharSequence text) {
                searchUser(text.toString());
                mSearchBar.disableSearch();
            }

            @Override
            public void onSearchStateChanged(boolean enabled) {
            }
            @Override
            public void onButtonClicked(int buttonCode) {
            }
        });
    }

    private void recyclerViewSetup() {
        mProgressBar = findViewById(R.id.progress_bar);
        mRvUser = findViewById(R.id.rv_user);
        mAdapter = new MainActivityAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvUser.setLayoutManager(linearLayoutManager);
        mRvUser.setHasFixedSize(true);
        mRvUser.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRvUser.setAdapter(mAdapter);

        mScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextPageUserData(page + 1);
            }
        };

        mRvUser.addOnScrollListener(mScrollListener);

        mAdapter.setOnItemClickListener((itemView, position) -> {
            Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
            intent.putExtra(UserProfileActivity.KEY_USER_LOGIN, mAdapter.getData(position).getLogin());
            startActivity(intent);
//            Toast.makeText(
//                    this,
//                    "Clicked On Position = " + position,
//                    Toast.LENGTH_SHORT).show();
        });
    }

    private void searchUser(String searchQuery) {
        mViewModel.searchUser(searchQuery);
    }

    private void loadNextPageUserData(int page) {
        Log.d(TAG, "Load Next User Data Page = " + page);

        mViewModel.loadNextPageUserData(page);
    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState((GithubSearchUserResponse) response.data);
                break;

            case UPDATE:
                renderUpdateState((GithubSearchUserResponse) response.data);
                break;

            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }

    private void renderLoadingState() {
        Log.d(TAG, "Entering Loading State");

        mRvUser.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void renderDataState(GithubSearchUserResponse data) {
        Log.d(TAG, "Entering Data State");

        mProgressBar.setVisibility(View.GONE);

        if (data.getTotalCount() == 0) {
            Toast.makeText(
                    this,
                    getResources().getString(R.string.error_user_not_found),
                    Toast.LENGTH_SHORT).show();

        } else {
            mAdapter.addNewData(data.getItems());
            mScrollListener.resetState();
            mRvUser.setVisibility(View.VISIBLE);
        }
    }

    private void renderUpdateState(GithubSearchUserResponse data) {
        Log.d(TAG, "Entering Update State");

        mAdapter.addData(data.getItems());
    }

    private void renderErrorState(Throwable error) {
        Log.d(TAG, "Entering Error State");

        mProgressBar.setVisibility(View.GONE);

        if (error instanceof HttpException) {
            Toast.makeText(
                    this,
                    getResources().getString(R.string.error_search_limit),
                    Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(
                    this,
                    getResources().getString(R.string.error_no_connection),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
