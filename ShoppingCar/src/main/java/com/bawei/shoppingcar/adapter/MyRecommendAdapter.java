package com.bawei.shoppingcar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.classify.view.DetailsPageActivity;
import com.bawei.shoppingcar.R;
import com.bawei.shoppingcar.entity.RecommendEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyRecommendAdapter extends RecyclerView.Adapter<MyRecommendAdapter.baseViewHolder> {

    private Context mContext;
    private List<RecommendEntity> list;

    public MyRecommendAdapter(Context mContext, List<RecommendEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public baseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_recommend, null);
        return new baseViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecommendAdapter.baseViewHolder holder, int position) {

        RecommendEntity recommendEntity = list.get(position);

        Glide.with(mContext).load(recommendEntity.getPictUrl()).into(holder.image);
        holder.price.setText(recommendEntity.getReservePrice());
        holder.title.setText(recommendEntity.getTitle());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsPageActivity.class);
                intent.putExtra("url",recommendEntity.getPictUrl());
                intent.putExtra("title",recommendEntity.getTitle());
                intent.putExtra("price",recommendEntity.getReservePrice());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class baseViewHolder extends RecyclerView.ViewHolder{

        private ImageView image,close;
        private TextView title,price;

        public baseViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_recycler_recommend_image);
            close = itemView.findViewById(R.id.item_recycler_recommend_close);
            title = itemView.findViewById(R.id.item_recycler_recommend_title);
            price = itemView.findViewById(R.id.item_recycler_recommend_price);
        }
    }

}
