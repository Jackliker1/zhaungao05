package com.bawei.shoppingcar.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.shoppingcar.entity.RecommendEntity;
import com.bawei.shoppingcar.repository.ShoppingCarRepository;

import java.util.List;

public class ShoppingCarViewModel extends BaseViewModel<ShoppingCarRepository> {

    public ShoppingCarViewModel(LifecycleOwner owner) {
        super(owner);
    }

    @Override
    protected ShoppingCarRepository createRepository() {
        return new ShoppingCarRepository();
    }

    public LiveData<BaseTokenEntity<List<RecommendEntity>>> getData(int page,int pageSize){
        return mRepository.getData(page,pageSize);
    }

    @Override
    protected void initRes() {

    }

    @Override
    protected void releaseRes() {

    }
}
