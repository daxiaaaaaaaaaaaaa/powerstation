package com.jilian.powerstation.utils;

import rx.Observer;

/**
 * 权限观察者的封装
 *
 *
 * @author weishixiong
 * @Time 2018-04-23
 */
public abstract class PermissionsObserver implements Observer<Boolean> {
    protected abstract void onGetPermissionsSuccess();

    protected abstract void onGetPermissionsSuccessFailuer();


    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
       // onGetPermissionsSuccessFailuer();
        ToastUitl.showLong("系统异常，请稍后再试");
    }

    @Override
    public void onNext(Boolean b) {
        if (b) {
            onGetPermissionsSuccess();
        } else {
            onGetPermissionsSuccessFailuer();
        }
    }
}
