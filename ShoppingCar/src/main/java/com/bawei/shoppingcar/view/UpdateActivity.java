package com.bawei.shoppingcar.view;

import androidx.databinding.ViewDataBinding;

import com.bawei.mvvm.view.MVVMBaseActivity;
import com.bawei.mvvm.viewmodel.BaseViewModel;

import java.util.HashMap;

public class UpdateActivity extends MVVMBaseActivity<BaseViewModel, ViewDataBinding> {
    @Override
    protected void prepareSetValues(HashMap<Integer, Object> mMap) {

    }

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEvn() {

    }
}
