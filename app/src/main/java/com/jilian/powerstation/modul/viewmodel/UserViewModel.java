package com.jilian.powerstation.modul.viewmodel;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseViewModel;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.vo.UserInfoVo;
import com.jilian.powerstation.common.vo.LoginVo;
import com.jilian.powerstation.factory.Factoty;
import com.jilian.powerstation.modul.repository.UserRepository;
import com.jilian.powerstation.utils.Md5Util;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户相关 viewmodel
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserViewModel extends BaseViewModel {
    private static final String TAG = "UserViewModel";
    /**
     * 注册
     */
    private LiveData<BaseDto> addUserliveData;
    /**
     * 获取验证码
     */
    private LiveData<BaseDto<BaseResultDto>> codeliveData;


    /**
     * 登陆
     */
    private LiveData<BaseDto<LoginDto>> loginliveData;


    /**
     * 注册
     *
     * @param accountName
     * @param accountEmail
     * @param verCode
     * @param accountPwd
     */
    public void addUserInfo(String accountName, String accountEmail, String verCode, String accountPwd) {
        UserInfoVo vo = new UserInfoVo();
        vo.setAccountName(accountName);
        vo.setAccountEmail(accountEmail);
        vo.setVerCode(verCode);
        vo.setAccountPwd(accountPwd);
        addUserliveData = Factoty.getRepository(UserRepository.class).addUserInfo(vo);
    }

    /**
     * 获取验证码
     *
     * @param accountEmail
     */
    public void getVerificationCode(String accountEmail) {
        UserInfoVo vo = new UserInfoVo();
        vo.setAccountEmail(accountEmail);
        codeliveData = Factoty.getRepository(UserRepository.class).getVerificationCode(vo);
    }


    /**
     * 登录
     *
     * @param accountEmail
     * @param accountPwd
     */
    public void login(String accountEmail, String accountPwd) {
        UserInfoVo vo = new UserInfoVo();
        vo.setAccountEmail(accountEmail);
        vo.setAccountPwd(Md5Util.getMd5(accountPwd));
        Log.e(TAG, "login: "+ Md5Util.getMd5(accountPwd));
        loginliveData = Factoty.getRepository(UserRepository.class).login(vo);
    }


}
