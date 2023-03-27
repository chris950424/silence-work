package com.silence.admin.domain.authority.role.service;

import com.silence.admin.domain.authority.role.entity.Role;
import com.silence.admin.domain.authority.role.repository.RoleRepository;
import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.admin.ui.entity.dto.RoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RoleService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Service
public interface RoleService {

     String updatePermissionForRole(Role role);

     String addPermissionForRole(Role role);

     List<Long> getRolePermissionsByRoleId(Long id);

     String updateRole(Role role);

     String addRole(Role role);

     String deleteRole(String id);

     String deletePermissionForRole(Role role);

     List<Role> roleList();
}
