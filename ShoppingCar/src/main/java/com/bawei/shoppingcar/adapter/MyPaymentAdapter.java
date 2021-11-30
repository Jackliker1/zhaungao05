package com.bawei.shoppingcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shoppingcar.R;
import com.bawei.shoppingcar.entity.PayEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyPaymentAdapter extends RecyclerView.Adapter<MyPaymentAdapter.baseViewHolder> {

    private Context mContext;
    private List<PayEntity> payEntities;

    public MyPaymentAdapter(Context mContext, List<PayEntity> payEntities) {
        this.mContext = mContext;
        this.payEntities = payEntities;
    }

    @NonNull
    @Override
    public baseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_payment, null);
        return new baseViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPaymentAdapter.baseViewHolder holder, int position) {
        Glide.with(mContext).load(payEntities.get(position).getGoodsImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return payEntities.size();
    }

    static class baseViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public baseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_recycler_payment_image);
        }
    }

}
