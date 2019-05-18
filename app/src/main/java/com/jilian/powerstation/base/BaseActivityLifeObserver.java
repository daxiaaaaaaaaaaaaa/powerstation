package com.jilian.powerstation.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

public interface BaseActivityLifeObserver extends BaseActivityPresenter, LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onCreate();

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    @Override
    public void onStart();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onResume();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    @Override
    public void onPause();

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    @Override
    public void onStop();
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void onDestroy() ;
}
