package com.bawei.classify.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.bawei.classify.entity.GoodsEntity;
import com.bawei.classify.entity.TypeEntity;
import com.bawei.classify.repository.ClassRepository;
import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bawei.okhttp.entity.BaseTokenEntity;

import java.util.List;

public class ClassifyViewModel extends BaseViewModel<ClassRepository> {

    public ClassifyViewModel(LifecycleOwner owner) {
        super(owner);
    }

    public LiveData<BaseTokenEntity<List<TypeEntity>>> getTypeData(){
        return mRepository.getTypeData();
    }

    public LiveData<BaseTokenEntity<List<GoodsEntity>>> getGoodsData(String keyword, String type, int pageno, int pagesize){
        return mRepository.getGoodsData(keyword, type, pageno, pagesize);
    }

    @Override
    protected ClassRepository createRepository() {
        return new ClassRepository();
    }

    @Override
    protected void initRes() {

    }

    @Override
    protected void releaseRes() {

    }
}
