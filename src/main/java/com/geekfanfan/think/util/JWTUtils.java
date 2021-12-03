/*
* @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2021-12-02 13:56:47
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-02 15:51:36
 */
package com.geekfanfan.think.util;

import java.util.Calendar;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.geekfanfan.think.common.exception.Asserts;
import com.geekfanfan.think.entity.User;

import org.thymeleaf.util.StringUtils;

/**
 * @author admin
 */
public class JWTUtils {

    /**
     * 获取token
     * 
     * @param u user
     * @return token
     */
    public static String getToken(User u) {
        Calendar instance = Calendar.getInstance();
        // 默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId", u.getId())
                .withClaim("username", u.getUsername());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(u.getPassword()));
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token) throws Exception {
        if (StringUtils.isEmpty(token)) {
            // throw new MyException("token不能为空");
            Asserts.error("token不能为空");
        }

        // 获取登录用户真正的密码假如数据库查出来的是123456
        String password = "123456";
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        return build.verify(token);
    }

    /**
     * 获取token中的userid
     * 
     * @param token
     * @return
     * @throws JWTDecodeException
     */
    public static Integer getUserId(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("userId").asInt();
    }

    /*
     * public static void main(String[] args) {
     * DecodedJWT verify = verify(
     * "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTcxMDg1MDAsInVzZXJuYW1lIjoiYWRtaW4ifQ.geBEtpluViRUg66_P7ZisN3I_d4e32Wms8mFoBYM5f0"
     * );
     * System.out.println(verify.getClaim("password").asString());
     * }
     */
}
