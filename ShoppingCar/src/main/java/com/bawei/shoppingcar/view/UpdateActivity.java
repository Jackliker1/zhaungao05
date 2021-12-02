package com.bawei.shoppingcar.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewOverlay;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.mvvm.view.MVVMBaseActivity;
import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bawei.shoppingcar.BR;
import com.bawei.shoppingcar.R;
import com.bawei.shoppingcar.adapter.MyUpdateAdapter;
import com.bawei.shoppingcar.database.MySqlTable;
import com.bawei.shoppingcar.databinding.ActivityUpdateBinding;
import com.bawei.shoppingcar.entity.AddressEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateActivity extends MVVMBaseActivity<BaseViewModel, ActivityUpdateBinding> {

    private List<AddressEntity> list;
    private RecyclerView updateRecycler;

    @Override
    protected void prepareSetValues(HashMap<Integer, Object> mMap) {
        mMap.put(BR.ownerUpdate,this);
    }

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTableData();
    }

    @Override
    protected void initEvn() {

    }

    private void getTableData(){

        list = new ArrayList<>();

        updateRecycler = findViewById(R.id.update_recycler);
        updateRecycler.setLayoutManager(new LinearLayoutManager(this));

        setAddress();

        MyUpdateAdapter myUpdateAdapter = new MyUpdateAdapter(this, list);
        updateRecycler.setAdapter(myUpdateAdapter);

    }

    private void setAddress() {

        MySqlTable mySqlTable = new MySqlTable(this,"address.db",null,1);
        SQLiteDatabase db = mySqlTable.getReadableDatabase();

        Cursor address = db.query("address", null, null, null, null, null, null);
        while (address.moveToNext()){
            int id = address.getInt(address.getColumnIndex("id"));
            String name = address.getString(address.getColumnIndex("name"));
            String phone = address.getString(address.getColumnIndex("phone"));
            String province = address.getString(address.getColumnIndex("province"));
            String home = address.getString(address.getColumnIndex("home"));
            String place = address.getString(address.getColumnIndex("place"));
            int isCheck = address.getInt(address.getColumnIndex("isCheck"));
            AddressEntity addressEntity = new AddressEntity(id,name,phone,province,home,place,isCheck);
            list.add(addressEntity);
        }

    }

    public void goBack(View view){
        finish();
    }

    public void toAdd(View view){
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivity(intent);
    }

}
