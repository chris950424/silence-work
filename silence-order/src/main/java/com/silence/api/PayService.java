package com.silence.api;


/**
 *  CategoryService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface PayService {

    /**
     *  PayService
     * 
     * @author leo
     * @version 1.1.0
     * @date 2022/1/20
     */
    String pay(PayDto payDto);

    PayDto query(PayDto payDto);

}
