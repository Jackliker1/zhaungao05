package com.bawei.classify.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.classify.R;
import com.bawei.classify.entity.GoodsEntity;
import com.bawei.classify.entity.TypeEntity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MyListAdapter extends BaseQuickAdapter<TypeEntity, BaseViewHolder> {

    public MyListAdapter( @Nullable List<TypeEntity> data) {
        super(R.layout.item_list, data);
        addChildClickViewIds(R.id.item_recycler_tv);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, TypeEntity typeEntity) {
        View itemView = baseViewHolder.itemView;
        TextView type = baseViewHolder.itemView.findViewById(R.id.item_recycler_tv);
        baseViewHolder.setText(R.id.item_recycler_tv,typeEntity.getCategory_name());
        View view = baseViewHolder.itemView.findViewById(R.id.item_recycler_view);
        if(typeEntity.isVis()){
            view.setVisibility(View.VISIBLE);
            itemView.setBackgroundColor(Color.WHITE);
            type.getPaint().setFakeBoldText(true);
            type.setTextColor(Color.BLACK);
        }else{
            view.setVisibility(View.INVISIBLE);
            itemView.setBackgroundColor(Color.parseColor("#EEEEEE"));
            type.getPaint().setFakeBoldText(false);
            type.setTextColor(Color.GRAY);
        }
    }
}
