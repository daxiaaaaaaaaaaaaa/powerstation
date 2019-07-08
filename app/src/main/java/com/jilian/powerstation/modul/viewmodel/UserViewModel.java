package com.jilian.powerstation.modul.viewmodel;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseViewModel;
import com.jilian.powerstation.base.BaseVo;
import com.jilian.powerstation.common.dto.AlarmInfoDto;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.common.dto.PowerInfoDetailDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.common.vo.ForgetVo;
import com.jilian.powerstation.common.vo.PowerInfoVo;
import com.jilian.powerstation.common.vo.UpdatePwdVo;
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
     * 修改密码
     */
    private LiveData<BaseDto> forgetliveData;
    /**
     * 我的电站列表
     */
    private LiveData<BaseDto<PowerListDto>> powerListliveData;

    /**
     * 添加电站
     */
    private LiveData<BaseDto> addPowerliveData;
    /**
     * 获取电站详情
     */
    private LiveData<BaseDto<PowerInfoDetailDto>> powerliveData;
    /**
     * 个人信息
     */
    private LiveData<BaseDto<UserInfoDto>> userliveData;
    /**
     * 修改密码
     */
    private LiveData<BaseDto> resetPwdliveData;
    /**
     * 电站详情 获取警告
     */
    private LiveData<BaseDto<AlarmInfoDto>> alarmliveData;

    public LiveData<BaseDto<AlarmInfoDto>> getAlarmliveData() {
        return alarmliveData;
    }

    public static String getTAG() {
        return TAG;
    }

    public LiveData<BaseDto> getAddUserliveData() {
        return addUserliveData;
    }

    public LiveData<BaseDto<BaseResultDto>> getCodeliveData() {
        return codeliveData;
    }

    public LiveData<BaseDto<LoginDto>> getLoginliveData() {
        return loginliveData;
    }

    public LiveData<BaseDto> getForgetliveData() {
        return forgetliveData;
    }

    public LiveData<BaseDto<PowerListDto>> getPowerListliveData() {
        return powerListliveData;
    }

    public LiveData<BaseDto> getAddPowerliveData() {
        return addPowerliveData;
    }

    public LiveData<BaseDto<PowerInfoDetailDto>> getPowerliveData() {
        return powerliveData;
    }

    public LiveData<BaseDto<UserInfoDto>> getUserliveData() {
        return userliveData;
    }

    public LiveData<BaseDto> getResetPwdliveData() {
        return resetPwdliveData;
    }

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
        vo.setAccountPwd(Md5Util.getMd5(accountPwd));
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
        Log.e(TAG, "login: " + Md5Util.getMd5(accountPwd));
        loginliveData = Factoty.getRepository(UserRepository.class).login(vo);
    }

    /**
     * @param accountEmail
     * @param newPassword
     * @param verCode
     */
    public void forgetAndResetPassword(String accountEmail, String verCode, String newPassword) {
        ForgetVo vo = new ForgetVo();
        vo.setAccountEmail(accountEmail);
        vo.setNewPassword(Md5Util.getMd5(newPassword));
        vo.setVerCode(verCode);
        Log.e(TAG, "login: " + Md5Util.getMd5(newPassword));
        forgetliveData = Factoty.getRepository(UserRepository.class).forgetAndResetPassword(vo);
    }

    /**
     * 我的电站列表
     *
     * @param page
     * @param rows
     */
    public void getPowerList(Integer page, Integer rows) {
        BaseVo vo = new BaseVo();
        vo.setPage(page);
        vo.setRows(rows);
        powerListliveData = Factoty.getRepository(UserRepository.class).getPowerList(vo);
    }

    /***
     * 添加电站
     * @param powerSn SN 码
     * @param powerName 电站名称
     */
    public void addPowerInfo(String powerSn, String powerName) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        vo.setPowerName(powerName);
        addPowerliveData = Factoty.getRepository(UserRepository.class).addPowerInfo(vo);
    }

    /***
     * 获取电站详情
     * @param powerSn SN 码
     * @param powerName 电站名称
     */
    public void getPowerInfo(String powerSn, String powerName) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        vo.setPowerName(powerName);
        powerliveData = Factoty.getRepository(UserRepository.class).getPowerInfo(vo);
    }

    /**
     * 获取个人信息
     */
    public void getUserInfo() {
        userliveData = Factoty.getRepository(UserRepository.class).getUserInfo();
    }


    /**
     * @param rawAccountPwd 原密码
     * @param nowAccountPwd 新密码
     * @param confirmPwd    确认密码
     *                      加密
     */
    public void resetPassword(String rawAccountPwd, String nowAccountPwd, String confirmPwd) {
        UpdatePwdVo vo = new UpdatePwdVo();
        vo.setRawAccountPwd(Md5Util.getMd5(rawAccountPwd));
        vo.setNowAccountPwd(Md5Util.getMd5(nowAccountPwd));
        vo.setConfirmPwd(Md5Util.getMd5(confirmPwd));
        resetPwdliveData = Factoty.getRepository(UserRepository.class).resetPassword(vo);
    }


    /**
     * 电站详情  获取警告
     * @param powerSn
     * @param powerName
     */
    public void getPowerAlarmInfo(String powerSn, String powerName) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        vo.setPowerName(powerName);
        alarmliveData = Factoty.getRepository(UserRepository.class).getPowerAlarmInfo(vo);
    }


}
