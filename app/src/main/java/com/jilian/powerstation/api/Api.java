package com.jilian.powerstation.api;


import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.vo.LoginVo;
import com.jilian.powerstation.http.RequetRetrofit;

import io.reactivex.Flowable;


public class Api {


    /**
     * 登录接口
     *
     * @param vo
     * @return
     */

    public static Flowable<BaseDto<LoginDto>> login(LoginVo vo) {
        return RequetRetrofit.getInstance().login(vo);
    }




}
