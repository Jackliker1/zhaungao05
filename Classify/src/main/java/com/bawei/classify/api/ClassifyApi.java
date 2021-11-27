package com.bawei.classify.api;

import androidx.lifecycle.LiveData;

import com.bawei.classify.entity.GoodsEntity;
import com.bawei.classify.entity.TypeEntity;
import com.bawei.okhttp.entity.BaseTokenEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClassifyApi {

    @GET("api/GoodsType/getRecommendTypes")
    LiveData<BaseTokenEntity<List<TypeEntity>>> getTypeData();

    @GET("api/Goods/getGoods")
    LiveData<BaseTokenEntity<List<GoodsEntity>>> getGoodsData(@Query("keyword") String keyword, @Query("type") String type, @Query("pageno") int pageno, @Query("pagesize") int pagesize);

}
