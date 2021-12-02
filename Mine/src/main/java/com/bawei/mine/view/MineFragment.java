package com.bawei.mine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.mine.BR;
import com.bawei.mine.R;
import com.bawei.mine.databinding.FragmentMineBinding;
import com.bawei.mvvm.view.BaseLazyFragment;
import com.bawei.mvvm.view.MVVMBaseFragment;
import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bumptech.glide.Glide;

import java.util.HashMap;

public class MineFragment extends MVVMBaseFragment<BaseViewModel, FragmentMineBinding> {

    private ImageView mineHead;

    @Override
    protected void prepareValues(HashMap mMap) {
        mMap.put(BR.ownerMine,this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    protected void lazyLoadData() {
    }

    @Override
    protected void loadData() {

        initView();

        Glide.with(getContext()).load(R.drawable.head01).circleCrop().into(mineHead);

    }

    private void initView(){

        mineHead = (ImageView) findViewById(R.id.mine_head);

    }

}
