package com.bawei.home.model;

import androidx.lifecycle.LiveData;

import com.bawei.home.api.HomeApi;
import com.bawei.home.entity.Goods;
import com.bawei.home.entity.StyleEntity;
import com.bawei.mvvm.model.IModel;
import com.bawei.okhttp.factory.RetrofitFactory;
import com.bawei.okhttp.entity.BaseTokenEntity;

import java.util.List;

public class HomeModel implements IModel {

    public LiveData<BaseTokenEntity<List<StyleEntity>>> getStyleData(){
        HomeApi api = RetrofitFactory.getInstance().create(HomeApi.class);
        return api.getStyleData();
    }

    public LiveData<BaseTokenEntity<List<Goods>>> getGoodsData(String keyword,String type,int pageno,int pagesize){
        HomeApi api = RetrofitFactory.getInstance().create(HomeApi.class);
        return api.getGoodsData(keyword,type,pageno,pagesize);
    }

}
