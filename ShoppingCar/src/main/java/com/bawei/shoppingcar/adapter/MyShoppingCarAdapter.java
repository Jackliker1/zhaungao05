package com.bawei.shoppingcar.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.okhttp.database.MySql;
import com.bawei.shoppingcar.R;
import com.bawei.shoppingcar.ShoppingCarFragment;
import com.bawei.shoppingcar.entity.ShoppingCarEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyShoppingCarAdapter extends RecyclerView.Adapter<MyShoppingCarAdapter.BaseViewHolder> {

    private Context mContext;
    private List<ShoppingCarEntity> list;

    public MyShoppingCarAdapter(Context mContext, List<ShoppingCarEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.item_shopping_recycler, null);
        return new BaseViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyShoppingCarAdapter.BaseViewHolder holder, int position) {

        ShoppingCarEntity shoppingCarEntity = list.get(position);
        Glide.with(mContext).load(shoppingCarEntity.getImageUrl()).into(holder.item_shopping_image);
        holder.item_shopping_title.setText(shoppingCarEntity.getTitle());
        holder.item_shopping_num.setText(shoppingCarEntity.getNum() + "");
        holder.item_shopping_price.setText(shoppingCarEntity.getPrice() + "");

        for (int i = 0; i < list.size(); i++) {
            if(shoppingCarEntity.isCheck()){
                holder.item_shopping_cb.setChecked(true);
            }else{
                holder.item_shopping_cb.setChecked(false);
            }
        }

        holder.item_shopping_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = holder.item_shopping_cb.isChecked();
                shoppingCarEntity.setNum(shoppingCarEntity.getNum() + 1);
                shoppingCarEntity.setCheck(checked);
                holder.item_shopping_num.setText(String.valueOf(shoppingCarEntity.getNum()));
                MyShoppingCarAdapter.this.notifyItemChanged(position);
            }
        });

        holder.item_shopping_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCarEntity.setNum(shoppingCarEntity.getNum() - 1);
                if(shoppingCarEntity.getNum() == 0){
                    MySql mySql = new MySql(mContext,"ShopCar.db",null,1);
                    SQLiteDatabase db = mySql.getReadableDatabase();
                }else{
                    holder.item_shopping_num.setText(String.valueOf(shoppingCarEntity.getNum()));
                    MyShoppingCarAdapter.this.notifyItemChanged(position);
                }
            }
        });

        holder.item_shopping_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.item_shopping_cb.isChecked()){

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder{

        private CheckBox item_shopping_cb;
        private ImageView item_shopping_image;
        private TextView item_shopping_title,item_shopping_plus,item_shopping_num,item_shopping_subtract,item_shopping_price;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            item_shopping_cb = itemView.findViewById(R.id.item_shopping_cb);
            item_shopping_image = itemView.findViewById(R.id.item_shopping_image);
            item_shopping_title = itemView.findViewById(R.id.item_shopping_title);
            item_shopping_plus = itemView.findViewById(R.id.item_shopping_plus);
            item_shopping_subtract = itemView.findViewById(R.id.item_shopping_subtract);
            item_shopping_num = itemView.findViewById(R.id.item_shopping_num);
            item_shopping_price = itemView.findViewById(R.id.item_shopping_price);
        }
    }

}
