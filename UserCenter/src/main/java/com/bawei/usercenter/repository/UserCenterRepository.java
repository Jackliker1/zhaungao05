package com.bawei.usercenter.repository;

import androidx.lifecycle.LiveData;

import com.bawei.mvvm.common.InjectModel;
import com.bawei.mvvm.repository.BaseRepository;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.model.UserCenterModel;

public class UserCenterRepository extends BaseRepository {

    public UserCenterRepository() {
        super();
    }

    @InjectModel
    UserCenterModel userCenterModel;

    public LiveData<BaseTokenEntity<UserEntity>> register(String phoneNumber, String password){
        return userCenterModel.register(phoneNumber,password);
    }

    public LiveData<BaseTokenEntity<UserEntity>> login(String phoneNumber, String password){
        return userCenterModel.login(phoneNumber,password);
    }

}
