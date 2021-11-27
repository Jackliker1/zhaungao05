package com.bawei.home.view;

import com.bawei.home.BR;
import com.bawei.home.R;
import com.bawei.home.databinding.ActivitySearchBinding;
import com.bawei.mvvm.view.MVVMBaseActivity;
import com.bawei.mvvm.viewmodel.BaseViewModel;

import java.util.HashMap;

public class SearchActivity extends MVVMBaseActivity<BaseViewModel, ActivitySearchBinding> {

    @Override
    protected void prepareSetValues(HashMap mMap) {
        mMap.put(BR.ownerSearch,this);
    }

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initEvn() {

    }
}
