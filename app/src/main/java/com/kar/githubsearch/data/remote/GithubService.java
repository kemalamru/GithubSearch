package com.kar.githubsearch.data.remote;

import com.kar.githubsearch.data.model.GithubSearchUserResponse;
import com.kar.githubsearch.data.model.UserProfile;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

public interface GithubService {

    String ENDPOINT = "https://api.github.com/";

    // Search Github User
    @GET("search/users")
    Observable<GithubSearchUserResponse> searchUser(@Query("q") String searchQuery,
                                                    @Query("page") int page,
                                                    @Query("per_page") int per_page);

    // Get Github User Profile
    @GET("users/{login}")
    Observable<UserProfile> getUserProfile(@Path("login") String login);

    // Helper class that sets up a new services
    class Creator {

        public static GithubService newGithubService() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GithubService.ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(GithubService.class);
        }
    }
}
