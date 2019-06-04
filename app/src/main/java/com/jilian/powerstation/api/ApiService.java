package com.jilian.powerstation.api;


import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.LoginDto;
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
     * 表单提交
     *
     * @param vo
     * @return
     */
    @POST("/HESS_SCADA/app/user/login.app")
    Flowable<BaseDto<LoginDto>> login(@Body UserInfoVo vo);



}


