package com.example.biovision.core;

import okhttp3.ResponseBody;

public abstract class Result<T> {


    private Result() {
    }

    public static final class Success<T> extends Result<T> {
        public final T data;

        public Success(T data) {
            this.data = data;
        }
    }

    public static final class Error<T> extends Result<T> {
        public final Throwable exception;

        public Error(Throwable exception) {
            this.exception = exception;
        }
    }

    public static final class Loading<T> extends Result<T> {
        public Loading() {}
    }
}