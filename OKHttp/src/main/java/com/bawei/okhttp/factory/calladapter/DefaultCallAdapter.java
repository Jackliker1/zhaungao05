package com.bawei.okhttp.factory.calladapter;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

public class DefaultCallAdapter<R> implements CallAdapter<R,Object> {

    Type type = null;

    public DefaultCallAdapter(Type type) {
        this.type = type;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public Object adapt(Call<R> call) {
        return call;
    }
}
