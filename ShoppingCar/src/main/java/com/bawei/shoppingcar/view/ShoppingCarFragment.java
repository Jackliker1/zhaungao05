package com.bawei.shoppingcar.view;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bawei.mvvm.view.MVVMBaseFragment;
import com.bawei.okhttp.database.MySql;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bawei.shoppingcar.BR;
import com.bawei.shoppingcar.R;
import com.bawei.shoppingcar.adapter.MyRecommendAdapter;
import com.bawei.shoppingcar.databinding.FragmentShoppingcarBinding;
import com.bawei.shoppingcar.entity.RecommendEntity;
import com.bawei.shoppingcar.entity.ShoppingCarEntity;
import com.bawei.shoppingcar.viewmodel.ShoppingCarViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCarFragment extends MVVMBaseFragment<ShoppingCarViewModel, FragmentShoppingcarBinding> {

    private int page = 1;
    private int pageSize = 8;
    private int index = 0;
    private RecyclerView shoppingRecycler;
    private List<ShoppingCarEntity> list = new ArrayList<>();
    private CheckBox shoppingCheckAll;
    private TextView shoppingSumPrice;
    private TextView shoppingDelete;
    private Button shoppingCommit;
    private RecyclerView shoppingRecommendRecycler;

    @Override
    protected void prepareValues(HashMap<Integer, Object> mMap) {
        mMap.put(BR.ownerShoppingCar, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoppingcar;
    }

    @Override
    protected ShoppingCarViewModel createViewModel() {
        return new ShoppingCarViewModel(this);
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected void loadData() {

        initView();

        shoppingRecycler = (RecyclerView) findViewById(R.id.shopping_recycler);

        MySql mySql = new MySql(getContext(), "ShopCar.db", null, 1);
        SQLiteDatabase db = mySql.getReadableDatabase();

        Cursor goods = db.query("goods", null, null, null, null, null, null);
        while (goods.moveToNext()) {
            String title = goods.getString(goods.getColumnIndex("title"));
            String imageUrl = goods.getString(goods.getColumnIndex("pic"));
            float price = goods.getFloat(goods.getColumnIndex("price"));
            ShoppingCarEntity shoppingCarEntity = new ShoppingCarEntity(title, imageUrl, 1, price);
            list.add(shoppingCarEntity);
        }

        MyShoppingCarAdapter myShoppingCarAdapter = new MyShoppingCarAdapter(getContext(), list);

        shoppingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingRecycler.setAdapter(myShoppingCarAdapter);

        shoppingCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoppingCheckAll.isChecked()) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheck(true);
                    }
                    setPrice();
                    myShoppingCarAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheck(false);
                    }
                    setPrice();
                    myShoppingCarAdapter.notifyDataSetChanged();
                }
            }
        });

        mViewModel.getData(page,pageSize).observe(this, new Observer<BaseTokenEntity<List<RecommendEntity>>>() {
            @Override
            public void onChanged(BaseTokenEntity<List<RecommendEntity>> listBaseTokenEntity) {
                MyRecommendAdapter myRecommendAdapter = new MyRecommendAdapter(getContext(), listBaseTokenEntity.getData());
                shoppingRecommendRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                shoppingRecommendRecycler.setAdapter(myRecommendAdapter);

            }
        });

    }

    private void initView() {
        shoppingCheckAll = (CheckBox) findViewById(R.id.shopping_checkAll);
        shoppingSumPrice = (TextView) findViewById(R.id.shopping_sumPrice);
        shoppingDelete = (TextView) findViewById(R.id.shopping_delete);
        shoppingCommit = (Button) findViewById(R.id.shopping_commit);
        shoppingRecommendRecycler = (RecyclerView) findViewById(R.id.shopping_recommend_recycler);
    }

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
        public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

            ShoppingCarEntity shoppingCarEntity = list.get(position);
            Glide.with(mContext).load(shoppingCarEntity.getImageUrl()).into(holder.item_shopping_image);
            holder.item_shopping_title.setText(shoppingCarEntity.getTitle());
            holder.item_shopping_num.setText(shoppingCarEntity.getNum() + "");
            holder.item_shopping_price.setText(shoppingCarEntity.getPrice() + "");

            for (int i = 0; i < list.size(); i++) {
                if (shoppingCarEntity.isCheck()) {
                    holder.item_shopping_cb.setChecked(true);
                } else {
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
                    setPrice();
                    MyShoppingCarAdapter.this.notifyItemChanged(position);
                }
            });

            holder.item_shopping_subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shoppingCarEntity.getNum() == 0) {
                        Toast.makeText(mContext, "购物车商品最少为一个", Toast.LENGTH_SHORT).show();
                    } else {
                        shoppingCarEntity.setNum(shoppingCarEntity.getNum() - 1);
                        holder.item_shopping_num.setText(String.valueOf(shoppingCarEntity.getNum()));
                        setPrice();
                        MyShoppingCarAdapter.this.notifyItemChanged(position);
                    }
                }
            });

            holder.item_shopping_cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!holder.item_shopping_cb.isChecked()) {
                        shoppingCheckAll.setChecked(false);
                        shoppingCarEntity.setCheck(false);
                        setPrice();
                    } else {
                        shoppingCarEntity.setCheck(true);
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).isCheck()) {
                                index++;
                                MyShoppingCarAdapter.this.notifyItemChanged(position);
                            }
                            if (index == list.size()) {
                                shoppingCheckAll.setChecked(true);
                            }
                        }
                        setPrice();
                        index = 0;
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class BaseViewHolder extends RecyclerView.ViewHolder {

            private CheckBox item_shopping_cb;
            private ImageView item_shopping_image;
            private TextView item_shopping_title, item_shopping_plus, item_shopping_num, item_shopping_subtract, item_shopping_price;

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

    public void setPrice() {

        float price = 0;
        int index = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                price = list.get(i).getSumPrice() + price;
                index = list.get(i).getNum() + index;
            }
        }

        shoppingCommit.setText("去结算(" + index + ")");
        shoppingSumPrice.setText("合计：￥" + price);

    }

}

