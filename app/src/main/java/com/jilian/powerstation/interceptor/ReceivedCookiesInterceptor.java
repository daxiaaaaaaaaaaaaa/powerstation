package com.jilian.powerstation.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jilian.powerstation.Constant;
import com.jilian.powerstation.utils.SPUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 首次请求的处理
 * @author : weishixiong
 * @create : 18/05/03
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            List<String> cookies = new ArrayList<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            String cookieStr = JSONObject.toJSONString(cookies);
            SPUtil.putData(Constant.SP_VALUE.SP, Constant.SP_VALUE.SESSION_ID, cookieStr);
        }

        return originalResponse;
    }
}

