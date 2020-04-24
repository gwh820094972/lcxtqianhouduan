package com.gwh.seller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名服务
 * * 提供给SignAop使用
 * 通过授权编号获取到公钥
 */
@Service
public class SignService {
    static Map<String, String> PUBLIC_KEYS = new HashMap<>();

    //静态块初始化 授权编号为 1000 公钥为下面的一串可在util.test.java下的RSAUtilTest中查看
    //添加到PUBLIC_KEYS对象中
    static {
        PUBLIC_KEYS.put("1000","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDJX3ZscSAJMbSQzG4guTZAQVI\n" +
                "GSSeG3gvUo1x+hnMW6FcFXOY/QQYOFvDkrDmvuemO7id/zQf0Fy2k0oY9e5XdcSS\n" +
                "MxJHdxaSP+na5hyIr3RtQznaRoPWiiEeOe6FSt19CouTdFoPHLUp5uhkDobGz1hC\n" +
                "3FtN9G2nT3HC71V+xwIDAQAB");
    }

    /**
     * 根据授权编号返回公钥
     * @param authId
     * @return
     */
    public String getPublicKey(String authId){
        return PUBLIC_KEYS.get(authId);
    }
}
