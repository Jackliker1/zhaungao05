package com.bawei.okhttp.exception;

public class OkHttpException extends IllegalStateException {

    public OkHttpException(String context) {
        super(context);
    }

}
