package com.cisdi.transaction.util;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/2 15:25
 */



import com.alibaba.fastjson.JSON;
import com.cisdi.transaction.domain.dto.ResponseMsgDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

/**
 * @author zhang bifang
 * @date 2021-04-02 17:59
 */
@Slf4j
public class HttpUtil {
    /**
     *
     * @param url 地址
     * @param jsonParams json参数
     * @return
     */
    public static String postJson(String url,String jsonParams){
        return postConType(url,jsonParams,"application/json");
    }
    /**
     *
     * @param url 地址
     * @param jsonParams 键值对参数
     *   application/x-www-form-urlencoded
     * @return
     */
    public static String postUrlencoded(String url,String jsonParams){
       return postConType(url,jsonParams,"application/x-www-form-urlencoded");
    }

    /**
     *get请求
     * @param url 地址
     * @return
     */
    public static String get(String url){
        Request.Builder builder = new Request.Builder()
                .url(url);
        Request.Builder build = builder.get();
        Request request = build.build();
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return JSON.toJSONString(new ResponseMsgDTO());
        }

    }

    public static String postConType(String url, String jsonParams,String conType) {
        Request.Builder builder = new Request.Builder()
                .url(url);
        MediaType mediaType = MediaType.parse(conType+"; charset=utf-8");
        builder.post(RequestBody.create(mediaType, jsonParams));
        Request request = builder.build();
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return JSON.toJSONString(new ResponseMsgDTO());
        }
    }

    public static void main(String[] args) {
        System.out.println("11111");
    }
}