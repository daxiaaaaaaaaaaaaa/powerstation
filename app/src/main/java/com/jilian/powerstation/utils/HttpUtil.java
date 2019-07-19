package com.jilian.powerstation.utils;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jilian.powerstation.base.BaseVo;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * HttpUtil 工具类，把vo转换成map
 *
 * @author weishixiong
 * @Time 2018-03-19
 */
public class HttpUtil {

    /**
     * 上传单个对象的文件
     * 对象中包含文件数组
     * files转换为MultipartBody
     *
     * @param files
     * @return
     */
    public static MultipartBody filesToMultipartBody(BaseVo vo, List<File> files, String mediaType) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            //  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), file);
            builder.addFormDataPart("imgStream", file.getName(), requestBody);

        }
        if (EmptyUtils.isNotEmpty(vo)) {
            Map<String, String> map = HttpUtil.convertVo2Params(vo);
            for (String key : map.keySet()) {
                builder.addFormDataPart(key, map.get(key));
            }
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

    /**
     * 上传单组表单 多个文件
     * @param vo 表单
     * @param files 文件
     * @param mediaType
     * @return
     */
    public static MultipartBody uploadParamAndFiles(BaseVo vo, List<File> files, String mediaType) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if(EmptyUtils.isNotEmpty(files)) {
            for (File file : files) {
                //  这里为了简单起见，没有判断file的类型
                RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), file);
                builder.addFormDataPart("file", file.getName(), requestBody);

            }
        }
        if (EmptyUtils.isNotEmpty(vo)) {
            Map<String, String> map = HttpUtil.convertVo2Params(vo);
            for (String key : map.keySet()) {
                builder.addFormDataPart(key, map.get(key));
            }
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

    /**
     *
     * @param vo
     * @param oneList 正面
     * @param twoList 反面
     * @param mediaType
     * @return
     */
    public static MultipartBody uploadParamAndFiles(BaseVo vo, List<File> oneList,List<File> twoList, String mediaType) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if(EmptyUtils.isNotEmpty(oneList)) {
            for (File file : oneList) {
                //  这里为了简单起见，没有判断file的类型
                RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), file);
                builder.addFormDataPart("frontImg", file.getName(), requestBody);

            }
        }
        if(EmptyUtils.isNotEmpty(twoList)) {
            for (File file : twoList) {
                //  这里为了简单起见，没有判断file的类型
                RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), file);
                builder.addFormDataPart("backImg", file.getName(), requestBody);

            }
        }
        if (EmptyUtils.isNotEmpty(vo)) {
            Map<String, String> map = HttpUtil.convertVo2Params(vo);
            for (String key : map.keySet()) {
                builder.addFormDataPart(key, map.get(key));
            }
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

    /**
     *
     * @param vo
     * @param businessLicense
     * @param storeLogo
     * @param mediaType
     * @return
     */
    public static MultipartBody partnerApply(BaseVo vo, List<File> businessLicense,List<File> storeLogo, String mediaType) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if(EmptyUtils.isNotEmpty(businessLicense)) {
            for (File file : businessLicense) {
                //  这里为了简单起见，没有判断file的类型
                RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), file);
                builder.addFormDataPart("businessLicense", file.getName(), requestBody);

            }
        }
        if(EmptyUtils.isNotEmpty(storeLogo)) {
            for (File file : storeLogo) {
                //  这里为了简单起见，没有判断file的类型
                RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), file);
                builder.addFormDataPart("storeLogo", file.getName(), requestBody);

            }
        }
        if (EmptyUtils.isNotEmpty(vo)) {
            Map<String, String> map = HttpUtil.convertVo2Params(vo);
            for (String key : map.keySet()) {
                builder.addFormDataPart(key, map.get(key));
            }
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

//    /**
//     * 上传多组表单 多个文件
//     * @param voList 表单
//     * @param files 文件
//     * @param mediaType
//     * @return
//     */
//    public static MultipartBody uploadParamsAndFiles(List<ServiceAreaVo> voList, List<File> files, String mediaType) {
//        MultipartBody.Builder builder = new MultipartBody.Builder();
//        if(EmptyUtils.isNotEmpty(files)){
//            //传多组文件
//            for (File file : files) {
//                //  这里为了简单起见，没有判断file的类型
//                RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), file);
//                builder.addFormDataPart("file", file.getName(), requestBody);
//
//            }
//        }
//        //传多组表单
//        if (EmptyUtils.isNotEmpty(voList)) {
//            for (int i = 0; i <voList.size() ; i++) {
//                Map<String, String> map = HttpUtil.convertVo2Params(voList.get(i));
//                for (String key : map.keySet()) {
//                    builder.addFormDataPart(key, map.get(key));
//                }
//            }
//        }
//        builder.setType(MultipartBody.FORM);
//        MultipartBody multipartBody = builder.build();
//        return multipartBody;
//    }

    /**
     * vo转换为Map
     *
     * @param vo
     * @return
     */
    public static RequestBody convertVo2Json(BaseVo vo) {
        Map maps = convertVo2Params(vo);
        JSONObject object = new JSONObject(maps);
        return RequestBody.create(MediaType.parse("Content-Type, application/json"),
                "{\"data\":" + object.toString() + "}");
    }


    /**
     * vo转换为Map
     *
     * @param vo
     * @return
     */
    public static Map<String, String> convertVo2Params(BaseVo vo) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map = gson.fromJson(gson.toJson(vo), type);
        // 移除值为空的键值对
        removeNullValue(map);
        return map;
    }
    /*移除Map中值为空的键值对*/
    public static void removeNullEntry(Map map) {
        removeNullKey(map);
        removeNullValue(map);
    }
    /*移除键为空的键值对*/
    public static void removeNullKey(Map map) {
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object obj = (Object) iterator.next();
            remove(obj, iterator);
        }
    }
    /*移除值为空的键值对*/
    public static void removeNullValue(Map map) {
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object obj = (Object) iterator.next();
            Object value = (Object) map.get(obj);
            remove(value, iterator);
        }
    }

    private static void remove(Object obj, Iterator iterator) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (str == null || str.trim().isEmpty()) {
                iterator.remove();
            }
        } else if (obj instanceof Collection) {
            Collection col = (Collection) obj;
            if (col == null || col.isEmpty()) {
                iterator.remove();
            }

        } else if (obj instanceof Map) {
            Map temp = (Map) obj;
            if (temp == null || temp.isEmpty()) {
                iterator.remove();
            }

        } else if (obj instanceof Object[]) {
            Object[] array = (Object[]) obj;
            if (array == null || array.length <= 0) {
                iterator.remove();
            }
        } else {
            if (obj == null) {
                iterator.remove();
            }
        }
    }

    /**
     * 多文件转换为 Map<String, RequestBody> bodyMap
     *
     * @param files
     * @param mediaType 文件类型
     * @return
     */
    public static Map<String, RequestBody> convertVo2BodyMap(List<File> files, String mediaType) {
        Map<String, RequestBody> bodyMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            bodyMap.put("file" + i + "\"; filename=\"" + files.get(i).getName(), RequestBody.create(MediaType.parse(mediaType), files.get(i)));
        }
        return bodyMap;
    }

}
