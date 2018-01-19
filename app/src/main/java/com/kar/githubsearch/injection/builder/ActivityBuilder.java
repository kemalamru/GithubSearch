package com.kar.githubsearch.injection.builder;

import com.kar.githubsearch.ui.main.MainActivity;
import com.kar.githubsearch.ui.main.MainActivityModule;
import com.kar.githubsearch.ui.user_profile.UserProfileActivity;
import com.kar.githubsearch.ui.user_profile.UserProfileActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {UserProfileActivityModule.class})
    abstract UserProfileActivity bindUserProfileActivity();
}
