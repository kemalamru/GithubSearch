package com.kar.githubsearch.ui.user_profile;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kar.githubsearch.R;
import com.kar.githubsearch.data.model.GithubSearchUserResponse;
import com.kar.githubsearch.data.model.UserProfile;
import com.kar.githubsearch.ui.main.MainActivityViewModel;
import com.kar.githubsearch.util.ImageUtility;
import com.kar.githubsearch.util.Response;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.HttpException;

import static com.kar.githubsearch.util.Response.ERROR;
import static com.kar.githubsearch.util.Response.LOADING;
import static com.kar.githubsearch.util.Response.SUCCESS;
import static com.kar.githubsearch.util.Response.UPDATE;

public class UserProfileActivity extends AppCompatActivity {

    private final String TAG = UserProfileActivity.class.getSimpleName();
    public static final String KEY_USER_LOGIN = "key_user_login";

    private ImageView mIvUser;
    private TextView mTvUserName;
    private TextView mTvUserId;
    private TextView mTvRepos;
    private TextView mTvFollowers;
    private TextView mTvFollowing;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    UserProfileActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UserProfileActivityViewModel.class);
        mViewModel.response().observe(this, response -> processResponse(response));

        String userLogin = getIntent().getStringExtra(KEY_USER_LOGIN);
        getUserProfile(userLogin);

        layoutSetup();
    }

    private void layoutSetup() {
        mIvUser = findViewById(R.id.iv_user);
        mTvUserName = findViewById(R.id.tv_user_name);
        mTvUserId = findViewById(R.id.tv_user_id);
        mTvRepos = findViewById(R.id.tv_repos);
        mTvFollowers = findViewById(R.id.tv_followers);
        mTvFollowing = findViewById(R.id.tv_following);
    }

    private void getUserProfile(String userLogin) {
        mViewModel.getUserProfle(userLogin);
    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState((UserProfile) response.data);
                break;

            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }

    private void renderLoadingState() {
        Log.d(TAG, "Entering Loading State");
    }

    private void renderDataState(UserProfile userProfile) {
        Log.d(TAG, "Entering Update State");

        ImageUtility.setImageCircleUrl(mIvUser, userProfile.getAvatarUrl());
        mTvUserName.setText(userProfile.getLogin());
        mTvUserId.setText("ID: " + String.valueOf(userProfile.getId()));
        mTvRepos.setText(String.valueOf(userProfile.getPublicRepos()));
        mTvFollowers.setText(String.valueOf(userProfile.getFollowers()));
        mTvFollowing.setText(String.valueOf(userProfile.getFollowing()));
    }

    private void renderErrorState(Throwable error) {
        Log.d(TAG, "Entering Error State");

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
