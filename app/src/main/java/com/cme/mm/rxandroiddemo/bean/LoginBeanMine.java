package com.cme.mm.rxandroiddemo.bean;

/**
 * Descriptions：登录的实体类
 * <p>
 * Author：ChenME
 * Date：10/20/2016
 * Email：ibelieve1210@163.com
 */
public class LoginBeanMine {

    private String userName;//验证码
    private String password;//手机唯一标识

    public LoginBeanMine(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}