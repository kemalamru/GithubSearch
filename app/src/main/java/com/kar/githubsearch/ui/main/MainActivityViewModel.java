package com.kar.githubsearch.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kar.githubsearch.data.GithubServiceRepository;
import com.kar.githubsearch.util.Response;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

public class MainActivityViewModel extends ViewModel{

    private GithubServiceRepository mRepository;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private final MutableLiveData<Response> mResponseLiveData = new MutableLiveData<>();

    private String mSearchQuery;

    public MainActivityViewModel(GithubServiceRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    public MutableLiveData<Response> response() {
        return mResponseLiveData;
    }

    public void searchUser(String searchQuery) {
        mSearchQuery = searchQuery;

        mDisposable.add(mRepository.searchUser(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> mResponseLiveData.setValue(Response.loading()))
                .subscribe(
                    response -> mResponseLiveData.setValue(Response.success(response)),
                    throwable -> mResponseLiveData.setValue(Response.error(throwable))
                )
        );
    }

    // Update User List Data for Infinite Scroll Recycler View
    public void loadNextPageUserData(int page) {
        if (mSearchQuery != null) {
            mDisposable.add(mRepository.loadNextPageUserData(mSearchQuery, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            response -> mResponseLiveData.setValue(Response.update(response)),
                            throwable -> mResponseLiveData.setValue(Response.error(throwable))
                    )
            );
        }
    }
}
