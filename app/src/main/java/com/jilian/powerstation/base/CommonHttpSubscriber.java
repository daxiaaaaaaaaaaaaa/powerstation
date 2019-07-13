package com.jilian.powerstation.base;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;


import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.exception.ApiException;
import com.jilian.powerstation.exception.ExceptionEngine;
import com.jilian.powerstation.exception.ServerException;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 * 自定义请服务器被观察者
 *
 * @author weishixiong
 * @Time 2018-04-12
 */
public class CommonHttpSubscriber<T> implements Subscriber<BaseDto<T>> {

    private static final String TAG = "CommonHttpSubscriber";
    //异常类
    private ApiException ex;

    public CommonHttpSubscriber() {
        data = new MutableLiveData();

    }

    private MutableLiveData<BaseDto<T>> data;

    public MutableLiveData<BaseDto<T>> get() {
        return data;
    }

    public void set(BaseDto<T> t) {
        this.data.setValue(t);
    }

    public void onFinish(BaseDto<T> t) {
        set(t);
    }

    @Override
    public void onSubscribe(Subscription s) {
        // 观察者接收事件 = 1个
        s.request(1);
    }

    @Override
    public void onNext(BaseDto<T> t) {
        //成功
        if (t.isSuccess()) {
            onFinish(t);
        }
        //登录失效
        else if(t.isLogOut()){
            MyApplication.getInstance().logout(t.getCode(),t.getMsg());
        }
        else {
            ex = ExceptionEngine.handleException(new ServerException(t.getCode(), t.getMsg()));
            getErrorDto(ex);
        }
    }

    @Override
    public void onError(Throwable t) {
        Log.e(TAG, "onError{}" + t);
        ex = ExceptionEngine.handleException(t);
        getErrorDto(ex);
    }

    /**
     * 初始化错误的dto
     *
     * @param ex
     */
    private void getErrorDto(ApiException ex) {
        BaseDto dto = new BaseDto();
        dto.setCode(ex.getCode());
        dto.setMsg(ex.getMsg());
        onFinish((BaseDto<T>) dto);
    }

    @Override
    public void onComplete() {
    }
}
