package com.bawei.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.home.R;
import com.bawei.home.entity.Goods;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Goods> goods;
    private Context context;

    public MyAdapter(List<Goods> goods, Context context) {
        this.goods = goods;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_recycler_image);
            title = itemView.findViewById(R.id.item_recycler_title);
            price = itemView.findViewById(R.id.item_recycler_price);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_recycler_home, null);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Goods goods = this.goods.get(position);
        Glide.with(context).load(goods.getPictUrl()).into(holder.imageView);
        holder.price.setText(goods.getReservePrice());
        holder.title.setText(goods.getTitle());
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }
}
