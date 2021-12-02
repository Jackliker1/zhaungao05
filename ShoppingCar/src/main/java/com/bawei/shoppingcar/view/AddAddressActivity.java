package com.bawei.shoppingcar.view;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.bawei.mvvm.view.MVVMBaseActivity;
import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bawei.shoppingcar.BR;
import com.bawei.shoppingcar.R;
import com.bawei.shoppingcar.database.MySqlTable;
import com.bawei.shoppingcar.databinding.ActivityAddAddressBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddAddressActivity extends MVVMBaseActivity<BaseViewModel, ActivityAddAddressBinding> {

    private CheckBox isDef;
    private EditText name,phone,province,home;
    private RadioButton place_home,place_work,place_school;
    private List<RadioButton> buttons;

    @Override
    protected void prepareSetValues(HashMap<Integer, Object> mMap) {
        mMap.put(BR.ownerAdd,this);
    }

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initEvn() {

    }

    public void preserve(View view){

        initView();

        MySqlTable mySqlTable = new MySqlTable(this,"address.db",null,1);
        SQLiteDatabase db = mySqlTable.getReadableDatabase();

        String name_str = name.getText().toString();
        String phone_str = phone.getText().toString();
        String province_str = province.getText().toString();
        String home_str = home.getText().toString();
        String place_str = "";
        int isCheck = -1;

        if (place_home.isChecked()) {
            place_str = "家";
        }else if (place_work.isChecked()) {
            place_str = "公司";
        }else if(place_school.isChecked()){
            place_str = "学校";
        }

        if(isDef.isChecked()){
            isCheck = 1;
        }else{
            isCheck = 0;
        }

        if(isDef.isChecked()){
            db.execSQL("update address set isCheck = ? where isCheck = ?",new Object[]{0,1});
        }

        if(name_str == null || phone_str == null || province_str == null || home_str == null){
            showToast("选项不能为空");
        }else{
            db.execSQL("insert into address values(?,?,?,?,?,?,?)",new Object[]{null,name_str,phone_str,province_str,home_str,place_str,isCheck});
        }

        finish();

    }

    public void checked(View view){

        initView();

        for (int i = 0; i < buttons.size(); i++) {
            if(view.getId() == buttons.get(i).getId()){
                buttons.get(i).setBackground(getDrawable(R.drawable.background_circle_check));
                buttons.get(i).setTextColor(Color.RED);
            }else{
                buttons.get(i).setBackground(getDrawable(R.drawable.sild_circle));
                buttons.get(i).setTextColor(Color.parseColor("#999999"));
            }
        }

    }

    private void initView(){

        buttons = new ArrayList<>();

        isDef = findViewById(R.id.add_isDef);

        name = findViewById(R.id.add_name);
        phone = findViewById(R.id.add_phone);
        province = findViewById(R.id.add_province);
        home = findViewById(R.id.add_home);

        place_home = findViewById(R.id.add_place_home);
        place_work = findViewById(R.id.add_place_work);
        place_school = findViewById(R.id.add_place_school);

        buttons.add(place_home);
        buttons.add(place_work);
        buttons.add(place_school);

    }

}
