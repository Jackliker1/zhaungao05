package com.bawei.home.repository;

import androidx.lifecycle.LiveData;

import com.bawei.home.entity.Goods;
import com.bawei.home.entity.StyleEntity;
import com.bawei.home.model.HomeModel;
import com.bawei.mvvm.common.InjectModel;
import com.bawei.mvvm.repository.BaseRepository;
import com.bawei.okhttp.entity.BaseTokenEntity;

import java.util.List;

public class HomeRepository extends BaseRepository {

    public HomeRepository(){
        super();
    }

    @InjectModel
    HomeModel homeModel;

    public LiveData<BaseTokenEntity<List<StyleEntity>>> getStyleData(){
        return homeModel.getStyleData();
    }

    public LiveData<BaseTokenEntity<List<Goods>>> getGoodsData(String keyword,String type,int pageno,int pagesize){
        return homeModel.getGoodsData(keyword, type, pageno, pagesize);
    }

}
