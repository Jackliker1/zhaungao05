package com.bawei.home.api;

import androidx.lifecycle.LiveData;

import com.bawei.home.entity.Goods;
import com.bawei.home.entity.StyleEntity;
import com.bawei.okhttp.entity.BaseTokenEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeApi {

    @GET("api/GoodsType/getRecommendTypes")
    LiveData<BaseTokenEntity<List<StyleEntity>>> getStyleData();

    @GET("api/Goods/getGoods")
    LiveData<BaseTokenEntity<List<Goods>>> getGoodsData(@Query("keyword") String keyword,@Query("type") String type,@Query("pageno") int pageno,@Query("pagesize") int pagesize);

}
