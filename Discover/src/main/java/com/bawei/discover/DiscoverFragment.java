package com.bawei.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.mvvm.view.BaseLazyFragment;

public class DiscoverFragment extends BaseLazyFragment {
    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, null);
        init(view);
        return view;
    }

    private void init(View view) {
    }

    @Override
    protected void loadData() {

    }
}
