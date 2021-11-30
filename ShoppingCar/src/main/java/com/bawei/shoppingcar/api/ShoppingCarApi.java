package com.bawei.shoppingcar.api;

import androidx.lifecycle.LiveData;

import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.shoppingcar.entity.RecommendEntity;
import com.bawei.shoppingcar.entity.ShoppingCarEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShoppingCarApi {

    @GET("api/Goods/getRecommendGoods")
    LiveData<BaseTokenEntity<List<RecommendEntity>>> getData(@Query("page") int page,@Query("pagesize") int pagesize);

}
