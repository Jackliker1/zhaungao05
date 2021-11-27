package com.bawei.okhttp.factory.convertfactory;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public class CustomRequestBody<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.get("application/json;charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        String s = new Gson().toJson(value);
        return RequestBody.create(MEDIA_TYPE,s);
    }
}
