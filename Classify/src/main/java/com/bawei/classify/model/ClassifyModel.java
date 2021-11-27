package com.bawei.classify.model;

import androidx.lifecycle.LiveData;

import com.bawei.classify.api.ClassifyApi;
import com.bawei.classify.entity.GoodsEntity;
import com.bawei.classify.entity.TypeEntity;
import com.bawei.mvvm.model.IModel;
import com.bawei.okhttp.factory.RetrofitFactory;
import com.bawei.okhttp.entity.BaseTokenEntity;

import java.util.List;

public class ClassifyModel implements IModel {

    public LiveData<BaseTokenEntity<List<TypeEntity>>> getTypeData(){
        ClassifyApi api = RetrofitFactory.getInstance().create(ClassifyApi.class);
        return api.getTypeData();
    }

    public LiveData<BaseTokenEntity<List<GoodsEntity>>> getGoodsData(String keyword,String type,int pageno,int pagesize){
        ClassifyApi api = RetrofitFactory.getInstance().create(ClassifyApi.class);
        return api.getGoodsData(keyword, type, pageno, pagesize);
    }

}
