package com.jilian.powerstation.api;


import com.jilian.powerstation.base.BaseDto;
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
import com.jilian.powerstation.common.dto.PowerCardDto;
import com.jilian.powerstation.common.dto.PowerInfoDetailDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.common.vo.ConfigInfoVo;
import com.jilian.powerstation.common.vo.ForgetVo;
import com.jilian.powerstation.common.vo.HistoryVo;
import com.jilian.powerstation.common.vo.PowerInfoVo;
import com.jilian.powerstation.common.vo.ReportVo;
import com.jilian.powerstation.common.vo.UpdatePwdVo;
import com.jilian.powerstation.common.vo.UserInfoVo;
import com.jilian.powerstation.http.RequetRetrofit;
import com.jilian.powerstation.utils.HttpUtil;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;


public class Api {
    /**
     * 注册
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto> addUserInfo(UserInfoVo vo) {
        return RequetRetrofit.getInstance().addUserInfo(vo);
    }

    /**
     * 获取验证码
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<BaseResultDto>> getVerificationCode(UserInfoVo vo) {
        return RequetRetrofit.getInstance().getVerificationCode(vo);
    }


    /**
     * 登录接口
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<LoginDto>> login(UserInfoVo vo) {
        return RequetRetrofit.getInstance().login(vo);
    }

    /**
     * 忘记密码确认更改
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto> forgetAndResetPassword(ForgetVo vo) {
        return RequetRetrofit.getInstance().forgetAndResetPassword(vo);
    }

    /**
     * 我的电站列表
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<PowerListDto>> getPowerList(BaseVo vo) {
        return RequetRetrofit.getInstance().getPowerList(vo);
    }

    /**
     * 添加电站
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto> addPowerInfo(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().addPowerInfo(vo);
    }

    /**
     * 获取电站
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<PowerInfoDetailDto>> getPowerInfo(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getPowerInfo(vo);
    }

    /**
     * 获取报告
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<ReportListDto>> getReportData(ReportVo vo) {
        return RequetRetrofit.getInstance().getReportData(vo);
    }

    /**
     * 获取个人信息接口
     *
     * @return
     */
    public static Flowable<BaseDto<UserInfoDto>> getUserInfo() {
        return RequetRetrofit.getInstance().getUserInfo();
    }

    /**
     * 修改密码
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto> resetPassword(UpdatePwdVo vo) {
        return RequetRetrofit.getInstance().resetPassword(vo);
    }

    /**
     *  修改个人信息
     * @param vo
     * @return
     */
    public static Flowable<BaseDto> updateUserInfo(UserInfoVo vo) {
        return RequetRetrofit.getInstance().updateUserInfo(vo);
    }



    /**
     * 电站名片
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<PowerCardDto>> getPowerCard(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getPowerCard(vo);
    }

    /**
     * 更新固件
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<BaseResultDto>> updatePowerFirmwareInfo(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().updatePowerFirmwareInfo(vo);
    }



    /**
     * 电站详情 获取警告
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<AlarmInfoDto>> getPowerAlarmInfo(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getPowerAlarmInfo(vo);
    }

    /**
     * 配置-电站设
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<ConfigInfoDto>> getConfigInfo(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getConfigInfo(vo);
    }

    /**
     * 电站设置-保存
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<BaseResultDto>> savePowerSetting(ConfigInfoVo vo) {
        return RequetRetrofit.getInstance().savePowerSetting(vo);
    }

    /**
     * 设备调试-告警信息
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<DeviceAlarmInfoListDto>> getDeviceAlarmInfo(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getDeviceAlarmInfo(vo);
    }

    /**
     * 逆变器列表
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<PcsInfoListDto>> getPcsInfoList(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getPcsInfoList(vo);
    }

    /**
     * 逆变器详情
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<PcsInfoDetailDto>> getPcsInfo(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getPcsInfo(vo);
    }


    /**
     * 电池列表
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<BatteryInfoListDto>> getBatteryInfoList(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getBatteryInfoList(vo);
    }

    /**
     * 电池详情
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<BatteryDetailDto>> getBatteryInfo(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getBatteryInfo(vo);
    }

    /**
     * 逆变器详情-逆变器运行数据-历史数据 展示图形
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<PcsHistoryDataListDto>> getPcsHistoryData(HistoryVo vo) {
        return RequetRetrofit.getInstance().getPcsHistoryData(vo);
    }

    /**
     * 电池信息-电池数据(图表数据)
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<BatteryDataListDto>> getBatteryData(HistoryVo vo) {
        return RequetRetrofit.getInstance().getBatteryData(vo);
    }

    /**
     *
     * @param files
     * @param mediaType
     * @return
     */
    public static Flowable<BaseDto> uploadHeadPortrait(List<File> files, String mediaType) {
        return RequetRetrofit.getInstance().uploadHeadPortrait(HttpUtil.filesToMultipartBody(null, files, mediaType));
    }

}
