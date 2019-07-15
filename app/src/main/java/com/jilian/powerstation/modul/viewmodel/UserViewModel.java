package com.jilian.powerstation.modul.viewmodel;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseViewModel;
import com.jilian.powerstation.base.BaseVo;
import com.jilian.powerstation.common.dto.AlarmInfoDto;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.BatteryDataListDto;
import com.jilian.powerstation.common.dto.BatteryDetailDto;
import com.jilian.powerstation.common.dto.BatteryInfoListDto;
import com.jilian.powerstation.common.dto.ConfigInfoDto;
import com.jilian.powerstation.common.dto.DeviceAlarmInfoListDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.dto.PcsHistoryDataListDto;
import com.jilian.powerstation.common.dto.PcsInfoDetailDto;
import com.jilian.powerstation.common.dto.PcsInfoListDto;
import com.jilian.powerstation.common.dto.PowerInfoDetailDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.common.vo.ConfigInfoVo;
import com.jilian.powerstation.common.vo.ForgetVo;
import com.jilian.powerstation.common.vo.HistoryVo;
import com.jilian.powerstation.common.vo.PowerInfoVo;
import com.jilian.powerstation.common.vo.UpdatePwdVo;
import com.jilian.powerstation.common.vo.UserInfoVo;
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
    /**
     * 配置-电站设置
     */
    private LiveData<BaseDto<ConfigInfoDto>> configliveData;
    /**
     * 保存配置
     */
    private LiveData<BaseDto<BaseResultDto>> saveliveData;

    /**
     * 告警信息列表
     */
    private LiveData<BaseDto<DeviceAlarmInfoListDto>> deviceAlarmInfoData;
    /**
     * 逆变器列表
     */
    private LiveData<BaseDto<PcsInfoListDto>> pcsInfoData;
    /**
     * 逆变器详情
     */
    private LiveData<BaseDto<PcsInfoDetailDto>> pcsInfoDetailData;
    /**
     * 电池列表
     */
    private LiveData<BaseDto<BatteryInfoListDto>> batteryInfoData;

    /**
     * 电池详情
     */
    private LiveData<BaseDto<BatteryDetailDto>> batteryDetailData;
    /**
     * 逆变器历史数据
     */
    private LiveData<BaseDto<PcsHistoryDataListDto>> pcsHistoryData;
    /**
     * 电池历史数据
     */
    private LiveData<BaseDto<BatteryDataListDto>> btyHistoryData;

    /**
     * 修改个人信息
     */
    private LiveData<BaseDto> updateUserliveData;

    public LiveData<BaseDto> getUpdateUserliveData() {
        return updateUserliveData;
    }

    public LiveData<BaseDto<BatteryDataListDto>> getBtyHistoryData() {
        return btyHistoryData;
    }

    public LiveData<BaseDto<PcsHistoryDataListDto>> getPcsHistoryData() {
        return pcsHistoryData;
    }

    public LiveData<BaseDto<BatteryInfoListDto>> getBatteryInfoData() {
        return batteryInfoData;
    }

    public LiveData<BaseDto<BatteryDetailDto>> getBatteryDetailData() {
        return batteryDetailData;
    }

    public LiveData<BaseDto<PcsInfoDetailDto>> getPcsInfoDetailData() {
        return pcsInfoDetailData;
    }

    public LiveData<BaseDto<PcsInfoListDto>> getPcsInfoData() {
        return pcsInfoData;
    }

    public LiveData<BaseDto<DeviceAlarmInfoListDto>> getDeviceAlarmInfoData() {
        return deviceAlarmInfoData;
    }

    public LiveData<BaseDto<BaseResultDto>> getSaveliveData() {
        return saveliveData;
    }

    public LiveData<BaseDto<ConfigInfoDto>> getConfigliveData() {
        return configliveData;
    }

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
    public void addUserInfo(String accountName, String accountEmail, String verCode, String accountPwd,String confirmPwd) {
        UserInfoVo vo = new UserInfoVo();
        vo.setAccountName(accountName);
        vo.setAccountEmail(accountEmail);
        vo.setVerCode(verCode);
        vo.setAccountPwd(Md5Util.getMd5(accountPwd));
        vo.setConfirmPwd(Md5Util.getMd5(confirmPwd));
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
     */
    public void getPowerInfo(String powerSn) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
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
     *
     * @param powerSn
     */
    public void getPowerAlarmInfo(String powerSn) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        alarmliveData = Factoty.getRepository(UserRepository.class).getPowerAlarmInfo(vo);
    }

    public void getConfigInfo(String powerSn) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        configliveData = Factoty.getRepository(UserRepository.class).getConfigInfo(vo);
    }

    /**
     * @param powerSn
     * @param workingType
     * @param soc
     * @param rechargePower_One
     * @param dischargePower_One
     * @param rechargePower_Two
     * @param dischargePower_Two
     * @param rechargeStartTime_One
     * @param rechargeEndTime_One
     * @param dischargeStartTime_One
     * @param dischargeEndTime_One
     * @param rechargeStartTime_Two
     * @param rechargeEndTime_Two
     * @param dischargeStartTime_Two
     * @param dischargeEndTime_Two
     */
    public void savePowerSetting(String powerSn, int workingType, String soc,
                                 String rechargePower_One, String dischargePower_One, String rechargePower_Two, String dischargePower_Two,

                                 String rechargeStartTime_One, String rechargeEndTime_One, String dischargeStartTime_One, String dischargeEndTime_One,
                                 String rechargeStartTime_Two, String rechargeEndTime_Two, String dischargeStartTime_Two, String dischargeEndTime_Two) {
        ConfigInfoVo vo = new ConfigInfoVo();
        vo.setPowerSn(powerSn);
        vo.setWorkingType(workingType);
        vo.setSoc(soc);
        vo.setRechargePower_One(rechargePower_One);
        vo.setDischargePower_One(dischargePower_One);
        vo.setRechargePower_Two(rechargePower_Two);
        vo.setDischargePower_Two(dischargePower_Two);

        vo.setRechargeStartTime_One(rechargeStartTime_One);
        vo.setRechargeEndTime_One(rechargeEndTime_One);
        vo.setDischargeStartTime_One(dischargeStartTime_One);
        vo.setDischargeEndTime_One(dischargeEndTime_One);

        vo.setRechargeStartTime_Two(rechargeStartTime_Two);
        vo.setRechargeEndTime_Two(rechargeEndTime_Two);
        vo.setDischargeStartTime_Two(dischargeStartTime_Two);
        vo.setDischargeEndTime_Two(dischargeEndTime_Two);


        saveliveData = Factoty.getRepository(UserRepository.class).savePowerSetting(vo);
    }

    /**
     * @param powerSn sn码
     * @param type    类型  统计类型（0：逆变器，1：电池，2：智能设备）
     */
    public void getDeviceAlarmInfo(String powerSn, int type) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        vo.setType(type);
        deviceAlarmInfoData = Factoty.getRepository(UserRepository.class).getDeviceAlarmInfo(vo);
    }

    /**
     * 获取逆变器列表
     *
     * @param powerSn
     */
    public void getPcsInfoList(String powerSn) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        pcsInfoData = Factoty.getRepository(UserRepository.class).getPcsInfoList(vo);
    }

    /**
     * 逆变器详情
     *
     * @param powerSn
     * @param id
     */
    public void getPcsInfo(String powerSn, String id) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        vo.setId(id);
        pcsInfoDetailData = Factoty.getRepository(UserRepository.class).getPcsInfo(vo);
    }

    /**
     * 电池列表
     *
     * @param powerSn
     */
    public void getBatteryInfoList(String powerSn) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        batteryInfoData = Factoty.getRepository(UserRepository.class).getBatteryInfoList(vo);
    }


    /**
     * 电池详情
     *
     * @param powerSn
     * @param id
     */
    public void getBatteryInfo(String powerSn, String id) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        vo.setId(id);
        batteryDetailData = Factoty.getRepository(UserRepository.class).getBatteryInfo(vo);
    }

    /**
     * @param powerSn   sn码
     * @param id        逆变器索引
     * @param type      统计类型（0:pv输入电压，1:PV输入电流，2:pv输入功率，3:电网输出电压，4:电网输出频率，5:电网输出电流，
     *                  6:电网输出功率，7:pv发电量，8:馈电网电量，9:电网用电量，10:负载用电量）
     * @param startTime 开始时间
     */
    public void getPcsHistoryData(String powerSn, String id, int type, String startTime) {
        HistoryVo vo = new HistoryVo();
        vo.setPowerSn(powerSn);
        vo.setId(id);
        vo.setType(type);
        vo.setStartTime(startTime);
        pcsHistoryData = Factoty.getRepository(UserRepository.class).getPcsHistoryData(vo);
    }

    /**
     * @param powerSn   sn码
     * @param id        逆变器索引
     * @param type     统计类型（0:电池电压，1:电池电流，2：温度，3:电池功率，4:电池SOC百分比）----电池
     * @param startTime 开始时间
     */
    public void getBatteryData(String powerSn, String id, int type, String startTime) {
        HistoryVo vo = new HistoryVo();
        vo.setPowerSn(powerSn);
        vo.setId(id);
        vo.setType(type);
        vo.setStartTime(startTime);
        btyHistoryData = Factoty.getRepository(UserRepository.class).getBatteryData(vo);
    }

    /**
     * 修改个人信息
     * @param accountName
     */
    public void updateUserInfo(String accountName ) {
        UserInfoVo vo = new UserInfoVo();
        vo.setAccountName(accountName);
        updateUserliveData = Factoty.getRepository(UserRepository.class).updateUserInfo(vo);
    }






}
