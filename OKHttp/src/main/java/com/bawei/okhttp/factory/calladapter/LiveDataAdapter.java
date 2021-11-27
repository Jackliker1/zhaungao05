package com.bawei.okhttp.factory.calladapter;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.okhttp.entity.BaseTokenEntity;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataAdapter<R> implements CallAdapter<R, LiveData<BaseTokenEntity<R>>> {

    private Type type;

    public LiveDataAdapter(Type type) {
        this.type = type;
    }

    @Override
    public Type responseType() {
        return this.type;
    }

    @Override
    public LiveData<BaseTokenEntity<R>> adapt(Call<R> call) {
        final MutableLiveData<BaseTokenEntity<R>> liveData = new MutableLiveData<>();
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if(Looper.getMainLooper().getThread() == Thread.currentThread()){
                    liveData.setValue((BaseTokenEntity<R>) response.body());
                }else{
                    liveData.postValue((BaseTokenEntity<R>) response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                BaseTokenEntity baseTokenEntity = new BaseTokenEntity();
                baseTokenEntity.setCode(-1);
                baseTokenEntity.setMsg(t.getMessage());
                if(Looper.getMainLooper().getThread() == Thread.currentThread()){
                    liveData.setValue(baseTokenEntity);
                }else{
                    liveData.postValue(baseTokenEntity);
                }
            }
        });
        return liveData;
    }
}
