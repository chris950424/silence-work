package com.silence.admin.infrastructure.dao;

import com.silence.admin.domain.authority.client.repository.po.ClientPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ClientMapper
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Repository
public interface ClientMapper {

    /**
     * z增加客户端
     *
     * @param po
     * @return
     */
    int addClient(@Param("po") ClientPo po);


    /**
     * 删除客户端
     *
     * @param clientId
     * @return
     */
    int deleteClient(@Param("clientId") String clientId);
}
