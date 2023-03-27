package com.silence.admin.domain.authority.client.service;

import com.silence.admin.domain.authority.client.entity.Client;
import com.silence.admin.domain.authority.client.repository.ClientRepository;

import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.admin.ui.entity.dto.ClientDTO;
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
public class ClientService {

    @Resource
    private ClientRepository repository;


    public ApiResponse<String> addClient(ClientDTO dto) {
        Client DO = new Client();
        DO.setClientId(dto.getClientId());
        DO.setClientSecret(dto.getClientSecret());
        DO.setAccessTokenValidity(dto.getAccessTokenValidity());
        DO.setAdditionalInformation(dto.getAdditionalInformation());
        DO.setScope(dto.getScope());
        DO.setResourceIds(dto.getResourceIds());
        DO.setWebServerRedirectUri(dto.getWebServerRedirectUri());
        DO.setAuthorizedGrantTypes(dto.getAuthorizedGrantTypes());
        DO.setRefreshTokenValidity(dto.getRefreshTokenValidity());
        DO.setArchived(0);
        DO.setTrusted(0);
        DO.setAutoapprove("false");
        int i = repository.addClient(DO);
        return ApiResponse.success(String.valueOf(i));
    }


    public ApiResponse<String> deleteClient(String clientId) {
        int i = repository.deleteClient(clientId);
        return ApiResponse.success(String.valueOf(i));
    }
}
