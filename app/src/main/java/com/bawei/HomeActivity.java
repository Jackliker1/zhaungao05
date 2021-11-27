package com.bawei;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bawei.adapter.MyFragmentAdapter;
import com.bawei.classify.view.ClassifyFragment;
import com.bawei.discover.DiscoverFragment;
import com.bawei.home.view.HomeFragment;
import com.bawei.home.view.SearchActivity;
import com.bawei.mine.MineFragment;
import com.bawei.mvvm.view.BaseActivity;
import com.bawei.myapp.R;
import com.bawei.shoppingcar.ShoppingCarFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private List<Fragment> fragments = new ArrayList<>();
    private RadioButton homeHomePage;
    private RadioButton homeClassify;
    private RadioButton homeDiscover;
    private RadioButton homeShoppingCar;
    private RadioButton homeMine;
    private RadioGroup homeGroup;
    private ViewPager homePager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    public void toSearchActivity(View view) {
        startActivity(new Intent(this, SearchActivity.class));
        overridePendingTransition(com.bawei.home.R.anim.base_slide_right_out, com.bawei.home.R.anim.base_slide_right_in);
    }

    @Override
    protected void initEvn() {

    }

    private void setViewPager() {

        fragments.add(new HomeFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new ShoppingCarFragment());
        fragments.add(new MineFragment());

        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);

        homePager.setAdapter(fragmentAdapter);

        homePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        homeHomePage.setChecked(true);
                        homeClassify.setChecked(false);
                        homeDiscover.setChecked(false);
                        homeShoppingCar.setChecked(false);
                        homeMine.setChecked(false);
                        break;
                    case 1:
                        homeHomePage.setChecked(false);
                        homeClassify.setChecked(true);
                        homeDiscover.setChecked(false);
                        homeShoppingCar.setChecked(false);
                        homeMine.setChecked(false);
                        break;
                    case 2:
                        homeHomePage.setChecked(false);
                        homeClassify.setChecked(false);
                        homeDiscover.setChecked(true);
                        homeShoppingCar.setChecked(false);
                        homeMine.setChecked(false);
                        break;
                    case 3:
                        homeHomePage.setChecked(false);
                        homeClassify.setChecked(false);
                        homeDiscover.setChecked(false);
                        homeShoppingCar.setChecked(true);
                        homeMine.setChecked(false);
                        break;
                    case 4:
                        homeHomePage.setChecked(false);
                        homeClassify.setChecked(false);
                        homeDiscover.setChecked(false);
                        homeShoppingCar.setChecked(false);
                        homeMine.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home_homePage:
                        homePager.setCurrentItem(0);
                        break;
                    case R.id.home_classify:
                        homePager.setCurrentItem(1);
                        break;
                    case R.id.home_discover:
                        homePager.setCurrentItem(2);
                        break;
                    case R.id.home_shoppingCar:
                        homePager.setCurrentItem(3);
                        break;
                    case R.id.home_mine:
                        homePager.setCurrentItem(4);
                        break;
                }
            }
        });

    }

    private void initView() {

        homeGroup = findViewById(R.id.home_Group);
        homeHomePage = findViewById(R.id.home_homePage);
        homeClassify = findViewById(R.id.home_classify);
        homeDiscover = findViewById(R.id.home_discover);
        homeShoppingCar = findViewById(R.id.home_shoppingCar);
        homeMine = findViewById(R.id.home_mine);
        homePager = findViewById(R.id.home_Pager);

        setViewPager();

    }
}
