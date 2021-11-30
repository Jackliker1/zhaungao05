package com.bawei.mvvm.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bawei.mvvm.exception.MVVMException;
import com.bawei.mvvm.viewmodel.BaseViewModel;

import java.util.HashMap;
import java.util.Map;

public abstract class MVVMBaseActivity<VM extends BaseViewModel,Binding extends ViewDataBinding> extends BaseActivity {

    protected VM mViewModel;
    protected Binding mBinding;
    private HashMap<Integer,Object> mMap = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.setContentView(this,getLayoutId());
        mBinding.setLifecycleOwner(this);
        mViewModel = createViewModel();
        super.onCreate(savedInstanceState);
        prepareSetValues(mMap);
        setValues(mBinding,mMap);
    }

    private void setValues(Binding mBinding, HashMap<Integer, Object> mMap) {
        if(mMap.size() == 0 || mMap == null){
            //throw new MVVMException("Please set values");
        }
        for (Map.Entry<Integer,Object> entry : mMap.entrySet()){
            mBinding.setVariable(entry.getKey(),entry.getValue());
        }
    }

    protected abstract void prepareSetValues(HashMap<Integer, Object> mMap);

    protected abstract VM createViewModel();

    protected abstract int getLayoutId();

}
