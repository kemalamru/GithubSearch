package com.kar.githubsearch.ui.user_profile;

import android.arch.lifecycle.ViewModelProvider;

import com.kar.githubsearch.ViewModelProviderFactory;
import com.kar.githubsearch.data.GithubServiceRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 1/19/18.
 */

@Module
public class UserProfileActivityModule {

    @Provides
    UserProfileActivityViewModel provideUserProfileActivityViewModel(GithubServiceRepository repository) {
        return new UserProfileActivityViewModel(repository);
    }

    @Provides
    ViewModelProvider.Factory provideUserProfileActivityViewModelProvider(UserProfileActivityViewModel userProfileActivityViewModel) {
        return new ViewModelProviderFactory<>(userProfileActivityViewModel);
    }
}
