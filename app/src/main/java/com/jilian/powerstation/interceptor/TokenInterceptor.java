package com.jilian.powerstation.interceptor;

import android.util.Log;

import com.jilian.powerstation.Constant;
import com.jilian.powerstation.utils.SPUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder().
                addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").
                addHeader("Accept-Encoding", "gzip, deflate").
                addHeader("Connection", "keep-alive").
                addHeader("Accept", "*/*").
                build();
        return chain.proceed(request);

    }
}