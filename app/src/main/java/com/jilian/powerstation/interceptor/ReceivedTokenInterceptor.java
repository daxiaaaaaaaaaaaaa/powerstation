package com.jilian.powerstation.interceptor;

import android.os.Build;
import android.text.TextUtils;

import com.jilian.powerstation.Constant;
import com.jilian.powerstation.utils.SPUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ReceivedTokenInterceptor implements Interceptor {
    private static final String TAG = "TokenInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder newBuilder = chain.request().newBuilder();
        String token = SPUtil.getData(Constant.SP_VALUE.SP, Constant.SP_VALUE.TOKEN, String.class, null);
        Request request = newBuilder
                .method(chain.request().method(), chain.request().body())
                .build();
        if(!TextUtils.isEmpty(token)){
            request.newBuilder().addHeader("token",token);
        }
        return chain.proceed(request);


    }
}