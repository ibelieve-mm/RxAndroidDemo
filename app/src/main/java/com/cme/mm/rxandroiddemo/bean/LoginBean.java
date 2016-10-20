package com.cme.mm.rxandroiddemo.bean;

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：10/20/2016
 * Email：ibelieve1210@163.com
 */
public class LoginBean {

    private String valiCode;//验证码
    private String imei;//手机唯一标识
    private String loginName;
    private String osCode;//登陆平台，1.Android

    public LoginBean(String loginName, String valiCode, String imei) {
        this.loginName = loginName;
        this.valiCode = valiCode;
        this.imei = imei;
        this.osCode = "1";
    }

    public LoginBean(String imei) {
        this.loginName = "18888888888";
        this.valiCode = "8888";
        this.imei = imei;
        this.osCode = "1";
    }
}
