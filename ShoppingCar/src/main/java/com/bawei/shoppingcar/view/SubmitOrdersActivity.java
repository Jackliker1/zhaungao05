package com.bawei.shoppingcar.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.mvvm.view.MVVMBaseActivity;
import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bawei.okhttp.database.MySql;
import com.bawei.shoppingcar.BR;
import com.bawei.shoppingcar.R;
import com.bawei.shoppingcar.adapter.MySubmitOrdersAdapter;
import com.bawei.shoppingcar.database.MySqlTable;
import com.bawei.shoppingcar.databinding.ActivitySubmitordersBinding;
import com.bawei.shoppingcar.entity.PayEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubmitOrdersActivity extends MVVMBaseActivity<BaseViewModel, ActivitySubmitordersBinding> {

    private int num = 0;
    private float sumPrice = 0;
    private SQLiteDatabase db;
    private List<PayEntity> payEntities = new ArrayList<>();
    private android.widget.TextView paymentAddressProvince;
    private android.widget.TextView paymentAddressHome;
    private android.widget.TextView paymentPhone;
    private androidx.recyclerview.widget.RecyclerView paymentGoodsImages;
    private android.widget.TextView paymentGoodsNum;
    private android.widget.TextView paymentGoodsPrice;
    private android.widget.TextView paymentGoodsFreight;
    private android.widget.TextView paymentBarPrice;

    @Override
    protected void prepareSetValues(HashMap mMap) {
        mMap.put(BR.ownerSubmitOrders,this);
    }

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submitorders;
    }

    @Override
    protected void initEvn() {

        initView();

        MySqlTable mySqlTable = new MySqlTable(this,"SqlTable.db",null,1);
        db = mySqlTable.getReadableDatabase();

        Cursor orderTable2 = db.query("orderTable2", null, null, null, null, null, null);

        while (orderTable2.moveToNext()){
            String goodsImg = orderTable2.getString(orderTable2.getColumnIndex("goodsimgurl"));
            int goodsCount = orderTable2.getInt(orderTable2.getColumnIndex("goodscount"));
            float goodsPrice = orderTable2.getFloat(orderTable2.getColumnIndex("goodsprice"));
            float goodsTotalValue = orderTable2.getFloat(orderTable2.getColumnIndex("goodstotalvalue"));
            int id = orderTable2.getInt(orderTable2.getColumnIndex("id"));
            String orderNumber = orderTable2.getString(orderTable2.getColumnIndex("ordernumber"));
            int goodsId = orderTable2.getInt(orderTable2.getColumnIndex("goodsid"));
            PayEntity payEntity = new PayEntity(id,orderNumber,goodsId,goodsImg,goodsPrice,goodsCount,goodsTotalValue);
            payEntities.add(payEntity);
        }

        MySubmitOrdersAdapter myPaymentAdapter = new MySubmitOrdersAdapter(this, payEntities);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        paymentGoodsImages.setLayoutManager(linearLayoutManager);
        paymentGoodsImages.setAdapter(myPaymentAdapter);

        for (int i = 0; i < payEntities.size(); i++) {
            sumPrice = sumPrice + payEntities.get(i).getGoodsTotalValue();
            num = num + payEntities.get(i).getGoodsCount();
        }
        paymentBarPrice.setText(sumPrice + "");
        paymentGoodsPrice.setText(sumPrice + "");
        paymentGoodsNum.setText("共 " + num + " 件");

    }

    public void payOrder(View view){

        MySql mySql = new MySql(this, "ShopCar.db", null, 1);
        SQLiteDatabase database = mySql.getReadableDatabase();

        for (int i = 0; i < payEntities.size(); i++) {
            db.execSQL("delete from orderTable2 where id = " + payEntities.get(i).getId());
            database.delete("goods","pic = ?",new String[]{payEntities.get(i).getGoodsImgUrl()});
            payEntities.remove(i);
        }

        Intent intent = new Intent(SubmitOrdersActivity.this, PaymentActivity.class);
        intent.putExtra("sumPrice",sumPrice);
        startActivity(intent);
        finish();

    }

    public void toUpdate(View view){



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySql mySql = new MySql(this, "ShopCar.db", null, 1);
        SQLiteDatabase database = mySql.getReadableDatabase();

        for (int i = 0; i < payEntities.size(); i++) {
            db.execSQL("delete from orderTable2 where id = " + payEntities.get(i).getId());
            payEntities.remove(i);
        }
    }

    private void initView() {
        paymentAddressProvince = findViewById(R.id.payment_address_province);
        paymentAddressHome = findViewById(R.id.payment_address_home);
        paymentPhone = findViewById(R.id.payment_phone);
        paymentGoodsImages = findViewById(R.id.payment_goods_images);
        paymentGoodsNum = findViewById(R.id.payment_goods_num);
        paymentGoodsPrice = findViewById(R.id.payment_goods_price);
        paymentGoodsFreight = findViewById(R.id.payment_goods_freight);
        paymentBarPrice = findViewById(R.id.payment_bar_price);
    }
}
