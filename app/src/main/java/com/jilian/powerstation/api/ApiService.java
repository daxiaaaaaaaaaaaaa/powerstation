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

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * api接口
 *
 * @author weishixiong
 * @Time 2018-04-2
 */

public interface ApiService {
    /**
     * 注册
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/addUserInfo.app")
    Flowable<BaseDto> addUserInfo(@Body UserInfoVo vo);

    /**
     * 获取验证码
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getVerificationCode.app")
    Flowable<BaseDto<BaseResultDto>> getVerificationCode(@Body UserInfoVo vo);

    /**
     * 登录
     * post
     *
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/login.app")
    Flowable<BaseDto<LoginDto>> login(@Body UserInfoVo vo);

    /**
     * 忘记密码确认更改
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/forgetAndResetPassword.app")
    Flowable<BaseDto> forgetAndResetPassword(@Body ForgetVo vo);

    /**
     * 电站列表
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getPowerList.app")
    Flowable<BaseDto<PowerListDto>> getPowerList(@Body BaseVo vo);


    /**
     * 添加电站
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/addPowerInfo.app")
    Flowable<BaseDto> addPowerInfo(@Body PowerInfoVo vo);

    /**
     * 获取电站
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getPowerInfo.app")
    Flowable<BaseDto<PowerInfoDetailDto>> getPowerInfo(@Body PowerInfoVo vo);


    /**
     * 获取报告数据
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getPowerInfoByTime.app")
    Flowable<BaseDto<ReportListDto>> getReportData(@Body ReportVo vo);

    /**
     * 获取个人信息接口
     * @return
     */
    @POST("/HESS_SCADA/app/user/getUserInfo.app")
    Flowable<BaseDto<UserInfoDto>> getUserInfo();

    /**
     * 修改密码
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/resetPassword.app")
    Flowable<BaseDto> resetPassword(@Body UpdatePwdVo vo);

    /**
     * 电站名片
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getPowerCard.app")
    Flowable<BaseDto<PowerCardDto>> getPowerCard(@Body PowerInfoVo vo);

    /**
     * 更新固件
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/updatePowerFirmwareInfo.app")
    Flowable<BaseDto<BaseResultDto>> updatePowerFirmwareInfo(@Body PowerInfoVo vo);


    /**
     * 电站详情获取警告
     *
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getPowerAlarmInfo.app")
    Flowable<BaseDto<AlarmInfoDto>> getPowerAlarmInfo(@Body PowerInfoVo vo);

    /**
     * 配置-电站设置
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getConfigInfo.app")
    Flowable<BaseDto<ConfigInfoDto>> getConfigInfo(@Body PowerInfoVo vo);



    /**
     * 电站设置-保存
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/savePowerSetting.app")
    Flowable<BaseDto<BaseResultDto>> savePowerSetting(@Body ConfigInfoVo vo);


    /**
     * 设备调试-告警信息
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getDeviceAlarmInfo.app")
    Flowable<BaseDto<DeviceAlarmInfoListDto>> getDeviceAlarmInfo(@Body PowerInfoVo vo);


    /**
     * 逆变器列表
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getPcsInfoList.app")
    Flowable<BaseDto<PcsInfoListDto>> getPcsInfoList(@Body PowerInfoVo vo);


    /**
     * 逆变器详情
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getPcsInfo.app")
    Flowable<BaseDto<PcsInfoDetailDto>> getPcsInfo(@Body PowerInfoVo vo);



    /**
     * 电池列表
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getBatteryInfoList.app")
    Flowable<BaseDto<BatteryInfoListDto>> getBatteryInfoList(@Body PowerInfoVo vo);


    /**
     * 电池详情
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getBatteryInfo.app")
    Flowable<BaseDto<BatteryDetailDto>> getBatteryInfo(@Body PowerInfoVo vo);

    /**
     * 逆变器详情-逆变器运行数据-历史数据 展示图形
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getPcsHistoryData.app")
    Flowable<BaseDto<PcsHistoryDataListDto>> getPcsHistoryData(@Body HistoryVo vo);


    /**
     * .电池信息-电池数据(图表数据)
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/getBatteryData.app")
    Flowable<BaseDto<BatteryDataListDto>> getBatteryData(@Body HistoryVo vo);
}


