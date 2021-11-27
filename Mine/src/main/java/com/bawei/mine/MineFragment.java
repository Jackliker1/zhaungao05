package com.bawei.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.mvvm.view.BaseLazyFragment;

public class MineFragment extends BaseLazyFragment {

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        init(view);
        return null;
    }

    private void init(View view) {
    }

    @Override
    protected void loadData() {

    }
}
