package com.jilian.powerstation.modul.repository;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.api.Api;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.CommonRepository;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.vo.UserInfoVo;
import com.jilian.powerstation.common.vo.LoginVo;


public class UserRepository extends CommonRepository {
    /**
     * 注册
     * @param vo
     * @return
     */
    public LiveData<BaseDto> addUserInfo(UserInfoVo vo) {
        return request(Api.addUserInfo(vo)).send().get();
    }

    /**
     * 获取验证码
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



}
