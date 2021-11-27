package com.bawei.usercenter.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.repository.UserCenterRepository;

public class UserCenterViewModel extends BaseViewModel<UserCenterRepository> {

    public MutableLiveData<UserEntity> data_Login = new MutableLiveData<>();
    public MutableLiveData<UserEntity> data_register = new MutableLiveData<>();

    public UserCenterViewModel(LifecycleOwner owner) {
        super(owner);
    }

    public LiveData<BaseTokenEntity<UserEntity>> register(String phoneNumber, String password){
        return mRepository.register(phoneNumber,password);
    }

    public LiveData<BaseTokenEntity<UserEntity>> login(String phoneNumber, String password){
        return mRepository.login(phoneNumber,password);
    }

    @Override
    protected UserCenterRepository createRepository() {
        return new UserCenterRepository();
    }

    @Override
    protected void initRes() {

    }

    @Override
    protected void releaseRes() {

    }
}
