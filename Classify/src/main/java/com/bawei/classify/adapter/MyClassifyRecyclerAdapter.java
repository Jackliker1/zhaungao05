package com.bawei.classify.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.classify.R;
import com.bawei.classify.entity.GoodsEntity;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyClassifyRecyclerAdapter extends RecyclerView.Adapter<MyClassifyRecyclerAdapter.BaseViewHolder> {

    private Context context;
    private List<GoodsEntity> list;

    public MyClassifyRecyclerAdapter(Context context, List<GoodsEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_recycler_classify, null);
        return new BaseViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BaseViewHolder holder, int position) {
        GoodsEntity goodsEntity = list.get(position);
        holder.title.setText(goodsEntity.getCategoryName());
        Glide.with(context).load(goodsEntity.getPictUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private ImageView image;

        public BaseViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_classify_recycler_title);
            image = itemView.findViewById(R.id.item_classify_recycler_image);
        }
    }

}
