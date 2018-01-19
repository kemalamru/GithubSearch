package com.kar.githubsearch.injection.component;

import android.app.Application;

import com.kar.githubsearch.GithubSearchApp;
import com.kar.githubsearch.injection.builder.ActivityBuilder;
import com.kar.githubsearch.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(GithubSearchApp app);

}
