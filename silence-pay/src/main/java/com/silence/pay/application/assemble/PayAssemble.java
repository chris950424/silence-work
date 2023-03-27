package com.silence.pay.application.assemble;

import com.silence.api.PayDto;
import com.silence.pay.domain.wechat.entity.Pay;
import com.silence.pay.infrastructure.wechat.DO.PayDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * CategoryConverter
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Mapper
public interface PayAssemble {

    //    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);
    PayAssemble INSTANCE = Mappers.getMapper(PayAssemble.class);

    Pay payDtoToPay(PayDto payDto);

    @Mapping(target = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PayDto payToPayDto(Pay pay);
}
