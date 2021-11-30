package com.bawei.shoppingcar.model;

import androidx.lifecycle.LiveData;

import com.bawei.mvvm.model.IModel;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.okhttp.factory.RetrofitFactory;
import com.bawei.shoppingcar.api.ShoppingCarApi;
import com.bawei.shoppingcar.entity.RecommendEntity;

import java.util.List;

public class ShoppingCarModel implements IModel {

    public LiveData<BaseTokenEntity<List<RecommendEntity>>> getData(int page,int pageSize){
        ShoppingCarApi api = RetrofitFactory.getInstance().create(ShoppingCarApi.class);
        return api.getData(page,pageSize);
    }

}
