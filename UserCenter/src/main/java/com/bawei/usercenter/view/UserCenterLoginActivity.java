package com.bawei.usercenter.view;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.bawei.mvvm.view.MVVMBaseActivity;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.usercenter.BR;
import com.bawei.usercenter.R;
import com.bawei.usercenter.databinding.ActivityUsercenterLoginBinding;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.viewmodel.UserCenterViewModel;

import java.util.HashMap;

public class UserCenterLoginActivity extends MVVMBaseActivity<UserCenterViewModel, ActivityUsercenterLoginBinding> {

    @Override
    protected void prepareSetValues(HashMap<Integer, Object> mMap) {
        mMap.put(BR.owner_login,mViewModel.data_Login);
        mMap.put(BR.owner_login,this);
    }

    public void toRegisterActivity(View view){
        this.startActivity(new Intent(this, UserCenterRegisterActivity.class));
    }

    public void login(View view){
        String username = mViewModel.data_Login.getValue().getUsername();
        String pwd = mViewModel.data_Login.getValue().getPwd();

        mViewModel.login(username,pwd).observe(this, new Observer<BaseTokenEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseTokenEntity<UserEntity> userEntityBaseTokenEntity) {
                if(userEntityBaseTokenEntity.getCode() == 200){
                    Toast.makeText(UserCenterLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected UserCenterViewModel createViewModel() {
        return new UserCenterViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_usercenter_login;
    }

    @Override
    protected void initEvn() {

        mViewModel.data_Login.setValue(new UserEntity());

    }
}
