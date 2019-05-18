package com.jilian.powerstation.base;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Repository基类
 *
 * @author weishixiong
 * @Time 2018-03-30
 */

public class CommonRepository<T> {

    public void getIntance(Class classe){

    }
    /**
     * RxJava Subscriber回调
     */
    private CommonHttpSubscriber<T> commonHttpSubscriber;
    /**
     * 解决背压
     */
    private Flowable<BaseDto<T>> flowable;

    /**
     * 发送请求服务器事件
     */
    public CommonHttpSubscriber<T> send() {

            flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(commonHttpSubscriber);
        return commonHttpSubscriber;
    }

    /**
     * 初始化
     * commonHttpSubscriber = new Common
     */
    public CommonRepository() {
        commonHttpSubscriber = new CommonHttpSubscriber<>();
    }

    /**
     * 初始化flowable
     *
     * @param flowable
     * @return
     */
    public CommonRepository<T> request(Flowable<BaseDto<T>> flowable) {
        this.flowable = flowable;
        return this;
    }

}
