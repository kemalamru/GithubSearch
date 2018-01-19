package com.kar.githubsearch.ui.user_profile;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kar.githubsearch.data.GithubServiceRepository;
import com.kar.githubsearch.data.model.UserProfile;
import com.kar.githubsearch.util.Response;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kemal Amru Ramadhan on 1/19/18.
 */

public class UserProfileActivityViewModel extends ViewModel {

    private GithubServiceRepository mRepository;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private MutableLiveData<Response> mResponseLiveData = new MutableLiveData<>();

    public UserProfileActivityViewModel(GithubServiceRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    public MutableLiveData<Response> response() {
        return mResponseLiveData;
    }

    public void getUserProfle(String userLogin) {
        mDisposable.add(mRepository.getUserProfile(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> mResponseLiveData.setValue(Response.loading()))
                .subscribe(
                        userProfile -> mResponseLiveData.setValue(Response.success(userProfile)),
                        throwable -> mResponseLiveData.setValue(Response.error(throwable))
                )
        );
    }
}
