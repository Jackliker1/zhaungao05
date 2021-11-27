package com.bawei.usercenter.model;

import androidx.lifecycle.LiveData;

import com.bawei.mvvm.model.IModel;
import com.bawei.okhttp.factory.RetrofitFactory;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.usercenter.api.UserCenterApi;
import com.bawei.usercenter.entity.UserEntity;

public class UserCenterModel implements IModel {

    public LiveData<BaseTokenEntity<UserEntity>> register(String phoneNumber,String password){
        UserCenterApi userCenterApi = RetrofitFactory.getInstance().create(UserCenterApi.class);
        return userCenterApi.register(new UserEntity(1,phoneNumber,password,"0",""));
    }

    public LiveData<BaseTokenEntity<UserEntity>> login(String phoneNumber,String password){
        UserCenterApi userCenterApi = RetrofitFactory.getInstance().create(UserCenterApi.class);
        return userCenterApi.login(new UserEntity(1,phoneNumber,password,"0",""));
    }

}
