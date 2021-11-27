package com.bawei.classify.adapter;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.classify.R;
import com.bawei.classify.entity.GoodsEntity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<GoodsEntity, BaseViewHolder> {

    public MyAdapter( @Nullable List<GoodsEntity> data) {
        super(R.layout.item_recycler_classify, data);
        addChildClickViewIds(R.id.item_classify_recycler_image);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GoodsEntity goodsEntity) {
        TextView title = baseViewHolder.itemView.findViewById(R.id.item_classify_recycler_title);
        title.setText(goodsEntity.getCategoryName());
        Glide.with(getContext()).load(goodsEntity.getPictUrl()).into((ImageView) baseViewHolder.itemView.findViewById(R.id.item_classify_recycler_image));
    }
}
