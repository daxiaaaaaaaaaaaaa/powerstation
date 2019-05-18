package com.jilian.powerstation.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrlUtils {
    public static String getUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        } else {
            if (url.contains(",")) {
                String[] urlList = url.split(",");
                for (int i = 0; i < urlList.length; i++) {
                    if (!urlList[i].contains("mp4")) {
                        return urlList[i];
                    }
                }
            } else {
                return url;
            }
        }
        return url;
    }

    public static List<String> getUrlList(String url) {
        List<String> list = new ArrayList<>();
        if (TextUtils.isEmpty(url)) {
            return list;
        } else {
            if (url.contains(",")) {
                String[] array = url.split(",");
                list = new ArrayList<>(Arrays.asList(array));

            } else {
                list.add(url);

            }
            return list;
        }

    }
}
