package com.bawei.classify.repository;

import androidx.lifecycle.LiveData;

import com.bawei.classify.entity.GoodsEntity;
import com.bawei.classify.entity.TypeEntity;
import com.bawei.classify.model.ClassifyModel;
import com.bawei.mvvm.common.InjectModel;
import com.bawei.mvvm.repository.BaseRepository;
import com.bawei.okhttp.entity.BaseTokenEntity;

import java.util.List;

public class ClassRepository extends BaseRepository {

    @InjectModel
    ClassifyModel classifyModel;

    public ClassRepository() {
        super();
    }

    public LiveData<BaseTokenEntity<List<TypeEntity>>> getTypeData(){
        return classifyModel.getTypeData();
    }

    public LiveData<BaseTokenEntity<List<GoodsEntity>>> getGoodsData(String keyword, String type, int pageno, int pagesize){
        return classifyModel.getGoodsData(keyword, type, pageno, pagesize);
    }

}
