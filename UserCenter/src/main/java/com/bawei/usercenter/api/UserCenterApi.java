package com.bawei.usercenter.api;

import androidx.lifecycle.LiveData;

import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.usercenter.entity.UserEntity;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserCenterApi {

    @POST("api/User/register")
    LiveData<BaseTokenEntity<UserEntity>> register(@Body UserEntity userEntity);

    @POST("api/User/login")
    LiveData<BaseTokenEntity<UserEntity>> login(@Body UserEntity userEntity);

}
