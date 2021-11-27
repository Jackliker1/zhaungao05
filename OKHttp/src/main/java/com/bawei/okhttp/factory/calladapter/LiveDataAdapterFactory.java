package com.bawei.okhttp.factory.calladapter;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.bawei.okhttp.exception.OkHttpException;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import static android.content.ContentValues.TAG;

public class LiveDataAdapterFactory extends CallAdapter.Factory {


    public static LiveDataAdapterFactory create() {
        return new LiveDataAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        Class<?> rawType = CallAdapter.Factory.getRawType(returnType);
        if(rawType != LiveData.class){
            Log.i(TAG, "This is not LiveData");
            return null;
        }
        if(!(returnType instanceof ParameterizedType)){
            throw new OkHttpException("returnType must be parameterizedType");
        }
        if(rawType != LiveData.class && rawType != Call.class){
            throw new IllegalArgumentException("returnType must be LiveData or Call");
        }
        Type type = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);

        if(rawType == Call.class){
            return new DefaultCallAdapter<>(type);
        }else if(rawType == LiveData.class){
            return new LiveDataAdapter<>(type);
        }

        return new DefaultCallAdapter<>(type);
    }
}
