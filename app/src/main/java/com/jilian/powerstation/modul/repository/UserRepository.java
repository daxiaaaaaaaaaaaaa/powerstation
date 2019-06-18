package com.jilian.powerstation.modul.repository;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.api.Api;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseVo;
import com.jilian.powerstation.base.CommonRepository;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.common.dto.PowerListDto;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.common.vo.ForgetVo;
import com.jilian.powerstation.common.vo.PowerInfoVo;
import com.jilian.powerstation.common.vo.UpdatePwdVo;
import com.jilian.powerstation.common.vo.UserInfoVo;
import com.jilian.powerstation.common.vo.LoginVo;


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
     * 添加电站
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<PowerDto>> getPowerInfo(PowerInfoVo vo) {
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
     * @param vo
     * @return
     */
    public LiveData<BaseDto> resetPassword(UpdatePwdVo vo) {
        return request(Api.resetPassword(vo)).send().get();
    }




}
