package com.bawei.okhttp.factory.convertfactory;

import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.okhttp.entity.TokenApiEntity;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomResponseBody<T> implements Converter<ResponseBody,T> {

    private Type type;

    public CustomResponseBody(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        Gson gson = new Gson();
        String string = value.string();
        if(string.contains("access_")){
            return (T) gson.fromJson(string, TokenApiEntity.class);
        }
        BaseTokenEntity baseTokenEntity = gson.fromJson(string, BaseTokenEntity.class);
        if(baseTokenEntity.getCode() != 200){
            return (T)baseTokenEntity;
        }else{
            return gson.fromJson(string,type);
        }
    }
}
