package com.jilian.powerstation.modul.viewmodel;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseViewModel;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.vo.LoginVo;
import com.jilian.powerstation.factory.Factoty;
import com.jilian.powerstation.modul.repository.UserRepository;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户相关 viewmodel
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserViewModel extends BaseViewModel {
    /**
     * 登陆
     */
    private LiveData<BaseDto<LoginDto>> loginliveData;



    /**
     * 登录
     *
     * @param phone
     * @param pwd
     */
    public void login(String phone, String pwd, String location) {
        LoginVo vo = new LoginVo();
        vo.setPassword(pwd);
        vo.setPhone(phone);
        vo.setLocation(location);
        loginliveData = Factoty.getRepository(UserRepository.class).login(vo);
    }


}
