package com.jilian.powerstation.api;


import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.common.dto.LoginDto;
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
     * 登录
     * post
     * 表单提交
     *
     * @param vo
     * @return
     */
    @POST("/app/login")
    Flowable<BaseDto<LoginDto>> login(@Body LoginVo vo);



}


