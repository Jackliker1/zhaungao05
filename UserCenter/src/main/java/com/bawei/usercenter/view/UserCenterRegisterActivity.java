package com.bawei.usercenter.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.bawei.mvvm.view.MVVMBaseActivity;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.usercenter.BR;
import com.bawei.usercenter.R;
import com.bawei.usercenter.databinding.ActivityUsercenterRegisterBinding;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.viewmodel.UserCenterViewModel;

import java.util.HashMap;

public class UserCenterRegisterActivity extends MVVMBaseActivity<UserCenterViewModel, ActivityUsercenterRegisterBinding> {

    private android.widget.EditText UserCenterRegisterUserName;
    private android.widget.EditText UserCenterRegisterPassWord;

    @Override
    protected void prepareSetValues(HashMap<Integer, Object> mMap) {
        mMap.put(BR.user_register,mViewModel.data_register);
        mMap.put(BR.owner_register,this);
    }

    public void toLoginActivity(View view){
        this.startActivity(new Intent(this,UserCenterRegisterActivity.class));
    }

    public void register(View view){

        String phoneNumber = UserCenterRegisterUserName.getText().toString();
        String password = UserCenterRegisterPassWord.getText().toString();

        //String phoneNumber = mViewModel.data_register.getValue().getUsername();
        //String password = mViewModel.data_register.getValue().getPwd();
        mViewModel.register(phoneNumber,password).observe(this, new Observer<BaseTokenEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseTokenEntity<UserEntity> userEntityBaseTokenEntity) {
                if(userEntityBaseTokenEntity.getCode() == 200){
                    Toast.makeText(UserCenterRegisterActivity.this, "成功", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("123123", "onChanged: "+phoneNumber);
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
        return R.layout.activity_usercenter_register;
    }

    @Override
    protected void initEvn() {

        mViewModel.data_register.setValue(new UserEntity());

        initView();

    }

    private void initView() {
        UserCenterRegisterUserName = findViewById(R.id.UserCenter_Register_userName);
        UserCenterRegisterPassWord = findViewById(R.id.UserCenter_Register_passWord);
    }
}
