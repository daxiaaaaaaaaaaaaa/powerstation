package com.jilian.powerstation;

/**
 * 全局常量
 *
 * @author weishixiong
 * @Time 2018-03-16
 */

public class Constant {

    /**
     * 请求后台的所有API接口都在这里配置;统一管理
     */
    public static class Server {
        public final static int SUCCESS_CODE = 100;
        //服务器超时时间 16 秒
        public final static int TIME_OUT = 16;

        public final static int LOG_OUT_CODE = 10010;
        public static String BASE_URL = "http://218.17.28.106:2222/";
    }

    /**
     * sp 常量
     */
    public static class SP_VALUE {

        public static final String SP = "sp";//sp
        public static final String TOKEN = "token";//token
        public static final String SESSION_ID = "session_id";//
        public static final String LOGIN_DTO = "LOGIN_DTO";//登录实体key
    }

    /**
     * 全局常量
     */
    public static class FINALVALUE {
        public static final String CLIENT_TRUST_PASSWORD = "证书的密码";
        public static final String CLIENT_AGREEMENT = "TLS";//使用协议
        public static final String CLIENT_TRUST_KEYSTORE = "pkcs12";//bks pkcs12
        //证书
        public static final int CERTIFICATE = 0;//R.raw.formal_environment (把证书文件放到 raw 目录下)
        public static final String FILE_PROVIDER = "com.jilian.powerstation.fileprovider";



    }
}
