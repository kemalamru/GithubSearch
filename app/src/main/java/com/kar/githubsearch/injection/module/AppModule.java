package com.kar.githubsearch.injection.module;

import android.app.Application;
import android.content.Context;

import com.kar.githubsearch.data.remote.GithubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    GithubService provideGithubService() {
        return GithubService.Creator.newGithubService();
    }
}
