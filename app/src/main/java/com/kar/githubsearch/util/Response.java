package com.kar.githubsearch.util;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

public class Response {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ERROR, LOADING, SUCCESS, UPDATE})

    public @interface ResponseStatusDef {}

    public static final int ERROR = 0;
    public static final int LOADING = 1;
    public static final int SUCCESS = 2;
    public static final int UPDATE = 3;

    public final int status;

    @Nullable
    public final Object data;

    @Nullable
    public final Throwable error;

    private Response(@ResponseStatusDef int responseStatus, Object data, Throwable error) {
        this.status = responseStatus;
        this.data = data;
        this.error = error;
    }

    public static Response loading() {
        return new Response(LOADING, null, null);
    }

    public static Response success(@NonNull Object data) {
        return new Response(SUCCESS, data, null);
    }

    public static Response update(@NonNull Object data) {
        return new Response(UPDATE, data, null);
    }

    public static Response error(@NonNull Throwable error) {
        return new Response(ERROR, null, error);
    }
}
