package com.bawei.shoppingcar.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shoppingcar.R;
import com.bawei.shoppingcar.database.MySqlTable;
import com.bawei.shoppingcar.entity.AddressEntity;
import com.bawei.shoppingcar.view.UpdateActivity;

import java.util.List;

public class MyUpdateAdapter extends RecyclerView.Adapter<MyUpdateAdapter.baseViewHolder> {

    private Context mContext;
    private List<AddressEntity> list;

    public MyUpdateAdapter(Context mContext, List<AddressEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public baseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_update, parent,false);
        return new baseViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyUpdateAdapter.baseViewHolder holder, int position) {

        MySqlTable mySqlTable = new MySqlTable(mContext,"address.db",null,1);
        SQLiteDatabase db = mySqlTable.getReadableDatabase();

        AddressEntity addressEntity = list.get(position);

        if(addressEntity.getIsCheck() == 1){
            holder.place.setVisibility(View.VISIBLE);
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.def.setVisibility(View.VISIBLE);
        }else{
            holder.place.setVisibility(View.GONE);
            holder.checkBox.setVisibility(View.GONE);
            holder.def.setVisibility(View.GONE);
        }

        holder.place.setText(addressEntity.getPlace());
        holder.name.setText(addressEntity.getName());
        holder.phone.setText(addressEntity.getPhone());
        holder.address.setText(addressEntity.getProvince() + addressEntity.getHome());

        holder.set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("update address set isCheck = ? where isCheck = ?",new Object[]{0,1});
                db.execSQL("update address set isCheck = ? where id = ?",new Object[]{1,addressEntity.getId()});
                for (int i = 0; i < list.size(); i++) {
                    if(position == i){
                        list.get(i).setIsCheck(1);
                    }else{
                        list.get(i).setIsCheck(0);
                    }
                }
                MyUpdateAdapter.this.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class baseViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout set;
        private ImageView update;
        private RadioButton checkBox;
        private TextView name,phone,address,def,place;

        public baseViewHolder(@NonNull View itemView) {
            super(itemView);

            update = itemView.findViewById(R.id.item_update_update);
            checkBox = itemView.findViewById(R.id.item_update_check);
            name = itemView.findViewById(R.id.item_update_name);
            phone = itemView.findViewById(R.id.item_update_phone);
            address = itemView.findViewById(R.id.item_update_address);
            def = itemView.findViewById(R.id.item_update_default);
            place = itemView.findViewById(R.id.item_update_place);
            set = itemView.findViewById(R.id.item_update_set);

        }
    }

}
