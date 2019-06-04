package com.jilian.powerstation.api;


import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseVo;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.common.vo.ForgetVo;
import com.jilian.powerstation.common.vo.UserInfoVo;
import com.jilian.powerstation.common.vo.LoginVo;

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

}


