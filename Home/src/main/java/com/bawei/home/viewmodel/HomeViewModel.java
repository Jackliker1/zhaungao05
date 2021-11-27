package com.bawei.home.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.home.entity.Goods;
import com.bawei.home.entity.StyleEntity;
import com.bawei.home.repository.HomeRepository;
import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bawei.okhttp.entity.BaseTokenEntity;

import java.util.List;

public class HomeViewModel extends BaseViewModel<HomeRepository> {

    public MutableLiveData<BaseTokenEntity<List<StyleEntity>>> liveData = new MutableLiveData<>();

    public HomeViewModel(LifecycleOwner owner) {
        super(owner);
    }

    @Override
    protected HomeRepository createRepository() {
        return new HomeRepository();
    }

    public LiveData<BaseTokenEntity<List<StyleEntity>>> getStyleData(){
        return mRepository.getStyleData();
    }

    public LiveData<BaseTokenEntity<List<Goods>>> getGoodsData(String keyword,String type,int pageno,int pagesize){
        return mRepository.getGoodsData(keyword, type, pageno, pagesize);
    }

    @Override
    protected void initRes() {

    }

    @Override
    protected void releaseRes() {

    }
}
