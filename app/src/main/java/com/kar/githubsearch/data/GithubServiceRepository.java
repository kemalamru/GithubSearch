package com.kar.githubsearch.data;

import com.kar.githubsearch.data.model.GithubSearchUserResponse;
import com.kar.githubsearch.data.model.UserProfile;
import com.kar.githubsearch.data.remote.GithubService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

@Singleton
public class GithubServiceRepository {

    private final GithubService mGithubService;

    @Inject
    public GithubServiceRepository(GithubService githubService) {
        mGithubService = githubService;
    }

    public Observable<GithubSearchUserResponse> searchUser(String searchQuery) {
        return mGithubService.searchUser(searchQuery, 1, 100);
    }

    // Update User List Data for Infinite Scroll Recycler View
    public Observable<GithubSearchUserResponse> loadNextPageUserData(String searchQuery, int page) {
        return mGithubService.searchUser(searchQuery, page, 100);
    }

    public Observable<UserProfile> getUserProfile(String userLogin) {
        return mGithubService.getUserProfile(userLogin);
    }
}
