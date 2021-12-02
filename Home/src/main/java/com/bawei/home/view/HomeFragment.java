package com.bawei.home.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bawei.home.BR;
import com.bawei.home.R;
import com.bawei.home.adapter.MyAdapter;
import com.bawei.home.databinding.FragmentHomeBinding;
import com.bawei.home.entity.Goods;
import com.bawei.home.entity.StyleEntity;
import com.bawei.home.myview.MySearchBar;
import com.bawei.home.viewmodel.HomeViewModel;
import com.bawei.mvvm.view.MVVMBaseFragment;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends MVVMBaseFragment<HomeViewModel, FragmentHomeBinding> {

    private int page = 1;
    private int pageSize = 8;
    private Banner banner;
    private HomeViewModel mViewModel;
    private List<String> imageUrls;
    private List<RadioButton> radioButtons;
    private RadioButton homeBtn01;
    private RadioButton homeBtn02;
    private RadioButton homeBtn03;
    private RadioButton homeBtn04;
    private RadioButton homeBtn05;
    private RadioButton homeBtn06;
    private RadioButton homeBtn07;
    private RecyclerView homePageRecycler;
    private RadioGroup homeGroup;

    @Override
    protected void lazyLoadData() {


    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        init(view);
        return view;
    }

    @Override
    protected void prepareValues(HashMap<Integer, Object> mMap) {
        mMap.put(BR.ownerHome, this);
        mMap.put(BR.style,mViewModel.liveData);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeViewModel createViewModel() {
        return new HomeViewModel(this);
    }

    private void init(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void loadData() {

        initView();

        setBanner();

        mViewModel = new HomeViewModel(this);

        LiveData<BaseTokenEntity<List<StyleEntity>>> styleData = mViewModel.getStyleData();
        styleData.observe(this, new Observer<BaseTokenEntity<List<StyleEntity>>>() {
            @Override
            public void onChanged(BaseTokenEntity<List<StyleEntity>> list) {
                for (int i = 0; i < list.getData().size(); i++) {
                    RadioButton radioButton = radioButtons.get(i);
                    radioButton.setText(list.getData().get(i).getCategory_name());
                }
                if(!homeBtn01.getText().toString().equals("")){
                    if(homeBtn01.isChecked()){
                        setRecycler(homeBtn01.getText().toString(),homeBtn01.getText().toString());
                    }
                }
            }
        });

        homeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.home_Btn01) {
                    setRecycler(homeBtn01.getText().toString(),homeBtn01.getText().toString());
                }else if (checkedId == R.id.home_Btn02) {
                    setRecycler(homeBtn02.getText().toString(),homeBtn02.getText().toString());
                }else if (checkedId == R.id.home_Btn03) {
                    setRecycler(homeBtn03.getText().toString(),homeBtn03.getText().toString());
                }else if (checkedId == R.id.home_Btn04) {
                    setRecycler(homeBtn04.getText().toString(),homeBtn04.getText().toString());
                }else if (checkedId == R.id.home_Btn05) {
                    setRecycler(homeBtn05.getText().toString(),homeBtn05.getText().toString());
                }else if (checkedId == R.id.home_Btn06) {
                    setRecycler(homeBtn06.getText().toString(),homeBtn06.getText().toString());
                }else if (checkedId == R.id.home_Btn07) {
                    setRecycler(homeBtn07.getText().toString(),homeBtn07.getText().toString());
                }
            }
        });

    }

    public void setRecycler(String keyword,String type){

        LiveData<BaseTokenEntity<List<Goods>>> goodsData = mViewModel.getGoodsData(keyword, type, page, pageSize);

        goodsData.observe(this, new Observer<BaseTokenEntity<List<Goods>>>() {
            @Override
            public void onChanged(BaseTokenEntity<List<Goods>> listBaseTokenEntity) {
                MyAdapter myAdapter = new MyAdapter(listBaseTokenEntity.getData(),getContext());
                homePageRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                homePageRecycler.setAdapter(myAdapter);
            }
        });

    }

    public void setBanner() {

        imageUrls = new ArrayList<>();

        banner = (Banner) findViewById(R.id.home_Banner);

        imageUrls.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile03.16sucai.com%2F2017%2F1100%2F16sucai_p20161105180_441.JPG&refer=http%3A%2F%2Ffile03.16sucai.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1640157145&t=2c8070d5a3f00d371504adc4312c9be0");
        imageUrls.add("https://img1.baidu.com/it/u=3153146503,2867005401&fm=26&fmt=auto");
        imageUrls.add("https://img0.baidu.com/it/u=3528346097,525450860&fm=26&fmt=auto");

        banner.setImages(imageUrls);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(String.valueOf(path)).into(imageView);
            }
        }).start();

    }

    private void initView() {

        homeGroup = (RadioGroup) findViewById(R.id.home_Group);
        homeBtn01 = (RadioButton) findViewById(R.id.home_Btn01);
        homeBtn02 = (RadioButton) findViewById(R.id.home_Btn02);
        homeBtn03 = (RadioButton) findViewById(R.id.home_Btn03);
        homeBtn04 = (RadioButton) findViewById(R.id.home_Btn04);
        homeBtn05 = (RadioButton) findViewById(R.id.home_Btn05);
        homeBtn06 = (RadioButton) findViewById(R.id.home_Btn06);
        homeBtn07 = (RadioButton) findViewById(R.id.home_Btn07);
        homePageRecycler = (RecyclerView) findViewById(R.id.homePage_recycler);

        radioButtons = new ArrayList<>();
        radioButtons.add(homeBtn01);
        radioButtons.add(homeBtn02);
        radioButtons.add(homeBtn03);
        radioButtons.add(homeBtn04);
        radioButtons.add(homeBtn05);
        radioButtons.add(homeBtn06);
        radioButtons.add(homeBtn07);
    }
}
