package com.bawei.shoppingcar.repository;

import androidx.lifecycle.LiveData;

import com.bawei.mvvm.common.InjectModel;
import com.bawei.mvvm.repository.BaseRepository;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.shoppingcar.entity.RecommendEntity;
import com.bawei.shoppingcar.model.ShoppingCarModel;

import java.util.List;

public class ShoppingCarRepository extends BaseRepository {

    @InjectModel
    ShoppingCarModel mModel;

    public LiveData<BaseTokenEntity<List<RecommendEntity>>> getData(int page,int pageSize){
        return mModel.getData(page,pageSize);
    }

}
