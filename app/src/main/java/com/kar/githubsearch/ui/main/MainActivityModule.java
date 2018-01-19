package com.kar.githubsearch.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.kar.githubsearch.ViewModelProviderFactory;
import com.kar.githubsearch.data.GithubServiceRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModel provideMainActivityViewModel(GithubServiceRepository repository) {
        return new MainActivityViewModel(repository);
    }

    @Provides
    ViewModelProvider.Factory provideMainActivityViewModelProvider(MainActivityViewModel mainActivityViewModel) {
        return new ViewModelProviderFactory<>(mainActivityViewModel);
    }
}
