package com.jilian.powerstation.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.jilian.powerstation.base.CommonRepository;

/**
 *工厂类 提供对象
 */
public class Factoty {
    /**
     * 创建 Repository对象的工厂
     * @param t
     * @param <T>
     * @return
     */
    public static   <T extends CommonRepository> T getRepository(Class<T> t) {
        try {
            return t.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建ViewModel对象的工厂
     * @param clazz
     * @param appCompatActivity
     * @param <T>
     * @return
     */
    public static  <T extends ViewModel> T getViewModel(Class<T> clazz,AppCompatActivity appCompatActivity) {
        return ViewModelProviders.of(appCompatActivity).get(clazz);
    }
    /**
     * 创建ViewModel对象的工厂
     * @param clazz
     * @param fragment
     * @param <T>
     * @return
     */
    public static  <T extends ViewModel> T getViewModel(Class<T> clazz,Fragment fragment) {
        return ViewModelProviders.of(fragment).get(clazz);
    }
}
