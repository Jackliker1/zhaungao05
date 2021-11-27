package com.bawei.mvvm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bawei.mvvm.exception.MVVMException;
import com.bawei.mvvm.viewmodel.BaseViewModel;

import java.util.HashMap;
import java.util.Map;

public abstract class MVVMBaseFragment<VM extends BaseViewModel,Binding extends ViewDataBinding> extends BaseLazyFragment {

    protected VM mViewModel;
    protected Binding mBinding;
    private HashMap<Integer,Object> mMap = new HashMap<>();

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = createViewModel();
        mBinding = DataBindingUtil.inflate(inflater,getLayoutId(),null,false);
        mBinding.setLifecycleOwner(this);
        prepareValues(mMap);
        setValues(mBinding,mMap);
        return mBinding.getRoot();
    }

    private void setValues(Binding mBinding, HashMap<Integer, Object> mMap) {
        if(mMap == null || mMap.size() == 0){
            throw new MVVMException("please set Values");
        }
        for (Map.Entry<Integer,Object> entry:mMap.entrySet()){
            mBinding.setVariable(entry.getKey(),entry.getValue());
        }
    }

    protected abstract void prepareValues(HashMap<Integer, Object> mMap);

    protected abstract int getLayoutId();

    protected abstract VM createViewModel();
}
