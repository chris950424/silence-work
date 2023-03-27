package com.silence.admin.infrastructure.interceptor.mybatis;

/**
 * 验证类型枚举
 *
 * @author wangzhuhua
 * @date 2018/09/04 下午5:20
 **/
public enum CryptTypeEnum {
    /** AES加密（这个可是加密，不是脱敏） */
    AES,
    BES,
    /** 手机号 */
    @Deprecated
    PhoneNumber,
    /** 身份证 */
    @Deprecated
    IdCard,
    @Deprecated
    /** 银行卡 */
            BankCard
}