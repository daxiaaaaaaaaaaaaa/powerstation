package com.jilian.powerstation.api;


import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseVo;
import com.jilian.powerstation.common.dto.AlarmInfoDto;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.ConfigInfoDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.dto.PowerCardDto;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.common.dto.PowerInfoDetailDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.common.vo.ConfigInfoVo;
import com.jilian.powerstation.common.vo.ForgetVo;
import com.jilian.powerstation.common.vo.PowerInfoVo;
import com.jilian.powerstation.common.vo.UpdatePwdVo;
import com.jilian.powerstation.common.vo.ReportVo;
import com.jilian.powerstation.common.vo.UserInfoVo;
import com.jilian.powerstation.common.vo.LoginVo;
import com.jilian.powerstation.http.RequetRetrofit;

import io.reactivex.Flowable;
import retrofit2.http.Body;


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
     * 电站卡片 固件升级
     *
     * @param vo
     * @return
     */
    public static Flowable<BaseDto<PowerCardDto>> getPowerCard(PowerInfoVo vo) {
        return RequetRetrofit.getInstance().getPowerCard(vo);
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


}
