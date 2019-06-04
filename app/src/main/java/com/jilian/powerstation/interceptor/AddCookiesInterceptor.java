package com.jilian.powerstation.interceptor;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.jilian.powerstation.Constant;
import com.jilian.powerstation.utils.SPUtil;


import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 非首次请求的处理
 * @author : weishixiong
 * @create : 18/05/03
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String cookieStr = SPUtil.getData(Constant.SP_VALUE.SP, Constant.SP_VALUE.SESSION_ID, String.class, null);
        String token = SPUtil.getData(Constant.SP_VALUE.SP, Constant.SP_VALUE.TOKEN, String.class, null);
        List<String> cookies = JSONObject.parseArray(cookieStr, String.class);
        if (cookies != null) {
            for (String cookie : cookies) {
                builder.addHeader("Cookie", cookie);
                Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }
        if(!TextUtils.isEmpty(token)){
            builder.addHeader("token", token);
        }
        return chain.proceed(builder.build());
    }
}

