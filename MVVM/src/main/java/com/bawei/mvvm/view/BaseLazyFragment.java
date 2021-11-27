package com.bawei.mvvm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseLazyFragment extends BaseFragment{

    boolean isVisibleToUser = false;

    boolean isFirst = true;

    boolean isViewCreated = false;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            preformLoadData();
            this.isVisibleToUser = isVisibleToUser;
        }else{
            this.isVisibleToUser = false;
        }
    }

    private void preformLoadData() {
        if(isFirst && isVisibleToUser && isViewCreated){
            lazyLoadData();
            isFirst = false;
        }
    }

    protected abstract void lazyLoadData();
}
