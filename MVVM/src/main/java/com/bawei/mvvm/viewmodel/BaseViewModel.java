package com.bawei.mvvm.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.bawei.mvvm.repository.BaseRepository;

public abstract class BaseViewModel<Repo extends BaseRepository> extends ViewModel implements LifecycleObserver {

    protected Repo mRepository;
    protected LifecycleOwner mOwner;

    public BaseViewModel(LifecycleOwner owner) {
        mRepository = createRepository();
        owner.getLifecycle().addObserver(this);
        this.mOwner = owner;
    }

    protected abstract Repo createRepository();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void activityOnCreate(){
        initRes();
    }

    protected abstract void initRes();

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void activityOnStop(){
        releaseRes();
    }

    protected abstract void releaseRes();
}
