package com.bawei.mvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvn();
    }

    protected void toNextActivity(Class<?> tClass){
        this.startActivity(new Intent(this,tClass));
    }

    protected void toNextActivity(Class<?> tClass,Bundle bundle){
        this.startActivity(new Intent(this,tClass).putExtra("params",bundle));
    }

    protected void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected abstract void initEvn();
}
