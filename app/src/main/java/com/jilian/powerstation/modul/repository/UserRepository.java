package com.jilian.powerstation.modul.repository;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.api.Api;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.CommonRepository;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.vo.LoginVo;


public class UserRepository extends CommonRepository {
    /**
     * 登录
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<LoginDto>> login(LoginVo vo) {
        return request(Api.login(vo)).send().get();
    }



}
