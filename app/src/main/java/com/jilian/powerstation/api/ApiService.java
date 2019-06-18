package com.jilian.powerstation.api;


import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseVo;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.dto.PowerCardDto;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.common.vo.ForgetVo;
import com.jilian.powerstation.common.vo.PowerInfoVo;
import com.jilian.powerstation.common.vo.ReportVo;
import com.jilian.powerstation.common.vo.UpdatePwdVo;
import com.jilian.powerstation.common.vo.UserInfoVo;
import com.jilian.powerstation.common.vo.LoginVo;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

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
    Flowable<BaseDto<PowerDto>> getPowerInfo(@Body PowerInfoVo vo);


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
     * 电站卡片 - 固件信息
     * @param vo
     * @return
     */
    @GET("/HESS_SCADA/app.html?method=getPowerFirmwareInfo")
    Flowable<BaseDto<PowerCardDto>> getPowerCard(@QueryMap PowerInfoVo vo);

}


