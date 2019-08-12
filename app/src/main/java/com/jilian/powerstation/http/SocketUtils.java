package com.jilian.powerstation.http;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketUtils {
    private static final String TAG = "SocketUtils";

    public static void sandMessage(String msg) {
        new Thread() {
            @Override
            public void run() {
                newTheadSend(msg);
            }
        }.start();

    }

    /**
     * 发送消息
     *
     * @param msg
     */
    private static void newTheadSend(String msg) {

        try {

            //创建Socket对象
            Socket socket = new Socket("10.10.100.254", 8899);

            Log.e(TAG, "连接成功: " + msg);
            InputStream inputStream = socket.getInputStream();//获取一个输入流，接收服务端的信息
            //
            byte[] buf = new byte[2048];//此数字不唯一哦；
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                break;
            }
            String str = "";


            //起始符
            byte bytes2[] = new byte[2];
            bytes2[0] = buf[0];
            bytes2[1] = buf[1];
            String zero = new String (bytes2);
            str+= zero.replace("#","23");
            // 命令单元 命令标识
            str+=String.valueOf(buf[2]);
            // 命令单元 应答标志
            str+=String.valueOf(buf[3]);
            //设备识别码
            byte bytes17[] = new byte[17];
            bytes17[0]= buf[4];
            bytes17[1]= buf[5];
            bytes17[2]= buf[6];
            bytes17[3]= buf[7];
            bytes17[4]= buf[8];
            bytes17[5]= buf[9];
            bytes17[6]= buf[10];
            bytes17[7]= buf[11];
            bytes17[8]= buf[12];
            bytes17[9]= buf[13];
            bytes17[10]= buf[14];
            bytes17[11]= buf[15];
            bytes17[12]= buf[16];
            bytes17[13]= buf[17];
            bytes17[14]= buf[18];
            bytes17[15]= buf[19];
            bytes17[16]= buf[20];
            String four = new String (bytes17);
            str+=four;
            //数据来源
            str+= buf[21];
            //数据单元长度
            str+= buf[22];
            str+= buf[23];
            //数据单元
            byte[] unite = new byte[2];
            unite[0] = buf[22];
            unite[1] = buf[23];
            //数据单元总字节数
            int bites = bytesToInt(unite);

            for (int i =24 ; i <(bites+24) ; i++) {
                str+= buf[i];
            }

            Log.e(TAG, "newTheadSend: "+str );
            inputStream.close();//
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};


    /**
     * 将byte数组转换为整数
     * @param bs
     * @return
     */
    public static int bytesToInt(byte[] bs) {
        int a = 0;
        for (int i = bs.length - 1; i >= 0; i--) {
            a += bs[i] * Math.pow(255, bs.length - i - 1);
        }
        return a;
    }


}
