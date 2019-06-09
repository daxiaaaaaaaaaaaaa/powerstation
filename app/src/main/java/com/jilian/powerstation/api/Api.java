package com.jilian.powerstation.api;


import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseVo;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.common.vo.ForgetVo;
import com.jilian.powerstation.common.vo.PowerInfoVo;
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


}
