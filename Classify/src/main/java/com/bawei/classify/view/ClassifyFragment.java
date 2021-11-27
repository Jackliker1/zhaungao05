package com.bawei.classify.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bawei.classify.BR;
import com.bawei.classify.R;
import com.bawei.classify.adapter.MyAdapter;
import com.bawei.classify.adapter.MyListAdapter;
import com.bawei.classify.adapter.MyClassifyRecyclerAdapter;
import com.bawei.classify.entity.GoodsEntity;
import com.bawei.classify.entity.TypeEntity;
import com.bawei.classify.viewmodel.ClassifyViewModel;
import com.bawei.mvvm.view.MVVMBaseFragment;
import com.bawei.okhttp.entity.BaseTokenEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassifyFragment extends MVVMBaseFragment<ClassifyViewModel, ViewDataBinding>{

    private int id = 1;
    private int firstPage = 3;
    private int secondPage = 9;
    private int mColor = Color.parseColor("#EEEEEE");
    private int parentId = 0;
    private RecyclerView classifyRecycler;
    private List<TypeEntity> types = new ArrayList<>();
    private ClassifyViewModel mViewModel;
    private TextView classifyFirstTv;
    private RecyclerView classifyFirstRecycler;
    private TextView classifySecondTv;
    private RecyclerView classifySecondRecycler;
    private List<GoodsEntity> firstList;
    private List<GoodsEntity> secondList;

    @Override
    protected void prepareValues(HashMap mMap) {
        mMap.put(BR.ownerClassify, this);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        init(view);
        return view;
    }

    private void init(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected ClassifyViewModel createViewModel() {
        return new ClassifyViewModel(this);
    }

    @Override
    protected void lazyLoadData() {

    }

    private void initView() {

        classifyRecycler = (RecyclerView) findViewById(R.id.classify_recycler);
        classifyRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        classifyFirstTv = (TextView) findViewById(R.id.classify_first_tv);
        classifyFirstRecycler = (RecyclerView) findViewById(R.id.classify_first_recycler);
        classifySecondTv = (TextView) findViewById(R.id.classify_second_tv);
        classifySecondRecycler = (RecyclerView) findViewById(R.id.classify_second_recycler);

    }

    @Override
    protected void loadData() {

        initView();

        mViewModel = new ClassifyViewModel(this);
        mViewModel.getTypeData().observe(this, new Observer<BaseTokenEntity<List<TypeEntity>>>() {
            @Override
            public void onChanged(BaseTokenEntity<List<TypeEntity>> list) {
                for (int i = 0; i < list.getData().size(); i++) {
                    if(i == 0){
                        list.getData().get(i).setVis(true);
                    }
                    types.add(list.getData().get(i));
                }

                MyListAdapter myListAdapter = new MyListAdapter(types);
                classifyRecycler.setAdapter(myListAdapter);
                classifyFirstTv.setText(list.getData().get(0).getCategory_name());
                classifySecondTv.setText(list.getData().get(1).getCategory_name());
                setRecycler(list.getData().get(0).getCategory_name(),list.getData().get(0).getCategory_name(),list.getData().get(1).getCategory_name(),list.getData().get(1).getCategory_name());

                myListAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                        for (int i = 0; i < list.getData().size(); i++) {
                            if(position == i){
                                list.getData().get(i).setVis(true);
                            }else{
                                list.getData().get(i).setVis(false);
                            }
                        }
                        myListAdapter.notifyDataSetChanged();

                        if(position != list.getData().size() - 1){
                            String category_name01 = myListAdapter.getItem(position).getCategory_name();
                            String category_name02 = myListAdapter.getItem(position + 1).getCategory_name();
                            classifyFirstTv.setText(category_name01);
                            classifySecondTv.setText(category_name02);
                            setRecycler(category_name01,category_name01,category_name02,category_name02);
                        }else{
                            String category_name01 = myListAdapter.getItem(position - 1).getCategory_name();
                            String category_name02 = myListAdapter.getItem(position).getCategory_name();
                            classifyFirstTv.setText(category_name01);
                            classifySecondTv.setText(category_name02);
                            setRecycler(category_name01,category_name01,category_name02,category_name02);
                        }

                    }
                });

            }
        });

    }

    public void setRecycler(String firstKeyword, String firstType, String secondKeyword, String secondType) {
        mViewModel.getGoodsData(firstKeyword, firstType, id, firstPage).observe(this, new Observer<BaseTokenEntity<List<GoodsEntity>>>() {
            @Override
            public void onChanged(BaseTokenEntity<List<GoodsEntity>> list) {
                if(firstList != null){
                    firstList = null;
                }
                firstList = new ArrayList<>();
                for (int i = 0; i < list.getData().size(); i++) {
                    firstList.add(list.getData().get(i));
                }
                MyAdapter myAdapter = new MyAdapter(list.getData());
                MyClassifyRecyclerAdapter myClassifyRecyclerAdapter = new MyClassifyRecyclerAdapter(getContext(),firstList);
                classifyFirstRecycler.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                classifyFirstRecycler.setAdapter(myAdapter);

                myAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                        Intent intent = new Intent(getActivity(),DetailsPageActivity.class);
                        intent.putExtra("url",list.getData().get(position).getPictUrl());
                        intent.putExtra("price",list.getData().get(position).getReservePrice());
                        intent.putExtra("title",list.getData().get(position).getTitle());
                        startActivity(intent);
                    }
                });

            }
        });

        mViewModel.getGoodsData(secondKeyword, secondType, id, secondPage).observe(this, new Observer<BaseTokenEntity<List<GoodsEntity>>>() {
            @Override
            public void onChanged(BaseTokenEntity<List<GoodsEntity>> list) {
                if(secondList != null){
                    secondList = null;
                }
                secondList = new ArrayList<>();
                for (int i = 0; i < list.getData().size(); i++) {
                    secondList.add(list.getData().get(i));
                }
                MyAdapter myAdapter = new MyAdapter(list.getData());
                MyClassifyRecyclerAdapter myClassifyRecyclerAdapter = new MyClassifyRecyclerAdapter(getContext(),secondList);
                classifySecondRecycler.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                classifySecondRecycler.setAdapter(myAdapter);
                myAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                        Intent intent = new Intent(getActivity(),DetailsPageActivity.class);
                        intent.putExtra("url",list.getData().get(position).getPictUrl());
                        intent.putExtra("price",list.getData().get(position).getReservePrice());
                        intent.putExtra("title",list.getData().get(position).getTitle());
                        startActivity(intent);
                    }
                });
            }
        });

    }

}
