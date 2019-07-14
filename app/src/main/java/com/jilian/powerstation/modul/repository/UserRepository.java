package com.jilian.powerstation.modul.repository;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.api.Api;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseVo;
import com.jilian.powerstation.base.CommonRepository;
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


public class UserRepository extends CommonRepository {
    /**
     * 注册
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto> addUserInfo(UserInfoVo vo) {
        return request(Api.addUserInfo(vo)).send().get();
    }

    /**
     * 获取验证码
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<BaseResultDto>> getVerificationCode(UserInfoVo vo) {
        return request(Api.getVerificationCode(vo)).send().get();
    }


    /**
     * 登录
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<LoginDto>> login(UserInfoVo vo) {
        return request(Api.login(vo)).send().get();
    }

    /**
     * 忘记密码确认更改
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto> forgetAndResetPassword(ForgetVo vo) {
        return request(Api.forgetAndResetPassword(vo)).send().get();
    }

    /**
     * 我的电站列表
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<PowerListDto>> getPowerList(BaseVo vo) {
        return request(Api.getPowerList(vo)).send().get();
    }

    /**
     * 添加电站
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto> addPowerInfo(PowerInfoVo vo) {
        return request(Api.addPowerInfo(vo)).send().get();
    }

    /**
     * 获取电站详情
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<PowerInfoDetailDto>> getPowerInfo(PowerInfoVo vo) {
        return request(Api.getPowerInfo(vo)).send().get();
    }

    /**
     * 获取个人信息接口
     *
     * @return
     */
    public LiveData<BaseDto<UserInfoDto>> getUserInfo() {
        return request(Api.getUserInfo()).send().get();
    }

    /**
     * 修改密码
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto> resetPassword(UpdatePwdVo vo) {
        return request(Api.resetPassword(vo)).send().get();
    }


    /**
     *
     * 修改个人信息
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto> updateUserInfo(UserInfoVo vo) {
        return request(Api.updateUserInfo(vo)).send().get();
    }


    /**
     * 电站详情 获取警告
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<AlarmInfoDto>> getPowerAlarmInfo(PowerInfoVo vo) {
        return request(Api.getPowerAlarmInfo(vo)).send().get();
    }

    /**
     * 配置-电站设置
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<ConfigInfoDto>> getConfigInfo(PowerInfoVo vo) {
        return request(Api.getConfigInfo(vo)).send().get();
    }

    /**
     * 电站设置-保存
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<BaseResultDto>> savePowerSetting(ConfigInfoVo vo) {
        return request(Api.savePowerSetting(vo)).send().get();
    }

    /**
     * 设备调试-告警信息
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<DeviceAlarmInfoListDto>> getDeviceAlarmInfo(PowerInfoVo vo) {
        return request(Api.getDeviceAlarmInfo(vo)).send().get();
    }

    /**
     * 逆变器列表
     * @param vo
     * @return
     */
    public LiveData<BaseDto<PcsInfoListDto>> getPcsInfoList(PowerInfoVo vo) {
        return request(Api.getPcsInfoList(vo)).send().get();
    }

    /**
     * 逆变器详情
     * @param vo
     * @return
     */
    public LiveData<BaseDto<PcsInfoDetailDto>> getPcsInfo(PowerInfoVo vo) {
        return request(Api.getPcsInfo(vo)).send().get();
    }



    /**
     * 电池列表
     * @param vo
     * @return
     */
    public LiveData<BaseDto<BatteryInfoListDto>> getBatteryInfoList(PowerInfoVo vo) {
        return request(Api.getBatteryInfoList(vo)).send().get();
    }
    /**
     * 电池详情
     * @param vo
     * @return
     */
    public LiveData<BaseDto<BatteryDetailDto>> getBatteryInfo(PowerInfoVo vo) {
        return request(Api.getBatteryInfo(vo)).send().get();
    }

    /**
     * 逆变器详情-逆变器运行数据-历史数据 展示图形
     * @param vo
     * @return
     */
    public LiveData<BaseDto<PcsHistoryDataListDto>> getPcsHistoryData(HistoryVo vo) {
        return request(Api.getPcsHistoryData(vo)).send().get();
    }


    /**
     * 电池信息-电池数据(图表数据)
     * @param vo
     * @return
     */
    public LiveData<BaseDto<BatteryDataListDto>> getBatteryData(HistoryVo vo) {
        return request(Api.getBatteryData(vo)).send().get();
    }










}
