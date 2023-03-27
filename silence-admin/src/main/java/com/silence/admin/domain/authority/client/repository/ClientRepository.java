package com.silence.admin.domain.authority.client.repository;

import com.silence.admin.domain.authority.client.entity.Client;
import com.silence.admin.domain.authority.client.repository.po.ClientPo;
import com.silence.admin.infrastructure.dao.ClientMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClientService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Service
public class ClientRepository {

    @Resource
    private ClientMapper clientMapper;

    @Resource
    PasswordEncoder encoder;


    public int addClient(Client DO) {
        return clientMapper.addClient(transferVoToPo(DO));
    }

    private ClientPo transferVoToPo(Client DO) {
        ClientPo po = new ClientPo();
        po.setClientId(DO.getClientId());
        po.setClientSecret(encoder.encode(DO.getClientSecret()));
        po.setAccessTokenValidity(DO.getAccessTokenValidity());
        po.setAdditionalInformation(DO.getAdditionalInformation());
        po.setScope(DO.getScope());
        po.setResourceIds(DO.getResourceIds());
        po.setWebServerRedirectUri(DO.getWebServerRedirectUri());
        po.setAuthorizedGrantTypes(DO.getAuthorizedGrantTypes());
        po.setRefreshTokenValidity(DO.getRefreshTokenValidity());
        po.setArchived(0);
        po.setTrusted(0);
        po.setAutoapprove("false");
        return po;
    }

    public int deleteClient(String clientId) {
        return clientMapper.deleteClient(clientId);
    }
}
