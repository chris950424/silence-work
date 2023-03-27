package com.silence.pay.infrastructure.repository.converter;

import com.silence.pay.domain.wechat.entity.Pay;
import com.silence.pay.infrastructure.wechat.DO.PayDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * CategoryConverter
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Mapper
public interface PayConverter {

    //    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);
    PayConverter INSTANCE = Mappers.getMapper(PayConverter.class);

     PayDO payToPayDo(Pay pay);
}
