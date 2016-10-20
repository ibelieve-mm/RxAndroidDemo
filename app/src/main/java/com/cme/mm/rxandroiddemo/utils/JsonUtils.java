package com.cme.mm.rxandroiddemo.utils;

import com.cme.mm.rxandroiddemo.aes.AESUtil;
import com.google.gson.Gson;

/**
 * Descriptions：Json工具类
 * <p>
 * Author：ChenME
 * Date：10/20/2016
 * Email：ibelieve1210@163.com
 */
public class JsonUtils {

    /**
     * 创建json数据
     *
     * @param
     * @return 返回Json字符串
     */
    public static String createJsonString(Object value) {
        Gson gson = new Gson();
        String string = gson.toJson(value);
        return string;
    }

    /**
     * 加密
     */
    public static String aes(String string) throws Exception {
        AESUtil en = new AESUtil();
        String encrypt = en.Encrypt(string);
        return encrypt;
    }

}
