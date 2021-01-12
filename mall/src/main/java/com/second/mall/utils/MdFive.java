package com.second.mall.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author yqs
 * @Comments
 * @Date 2020/12/14 14:12
 **/
@Component
public class MdFive {
    //password  要加密的密码
    //saltValue  盐值
    public String encrypt(String password,String saltValue){
        //创建一个MD5的盐值对象
        Object salt = new Md5Hash(saltValue);
        //生成加密的字符串
        Object result = new SimpleHash("MD5",password,salt,1024);

        return result + "";
    }

    //设置一个固定的盐值SALT，只根据密码进行加密
    private static final String SALT = "&%5123***&&%%$$#@";

    public static String getMD5(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String base = str + "/" + SALT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    //测试MD5密码输出
    public static void main(String[] args) {
        System.out.println(getMD5("123456"));
    }
}
