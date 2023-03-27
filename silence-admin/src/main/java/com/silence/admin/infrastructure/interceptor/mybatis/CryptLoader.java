package com.silence.admin.infrastructure.interceptor.mybatis;

import com.silence.admin.infrastructure.interceptor.mybatis.impl.AESCryptImpl;

/**
 * 脱敏实现类加载器(先简单实现)
 *
 * @author wangzhuhua
 * @date 2018/09/04 下午6:22
 **/
public class CryptLoader {

    /**
     * 加载所有加密方式实现类
     */
    public void loadCrypt() {
        CryptContext.setCrypt(CryptTypeEnum.AES, new AESCryptImpl());
        CryptContext.setCrypt(CryptTypeEnum.BES, new AESCryptImpl());
    }
}