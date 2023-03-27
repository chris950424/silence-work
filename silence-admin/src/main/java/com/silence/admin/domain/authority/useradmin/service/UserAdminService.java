package com.silence.admin.domain.authority.useradmin.service;

import com.silence.admin.domain.authority.useradmin.entity.UserAdmin;
import com.silence.admin.domain.authority.useradmin.repository.UserAdminRepository;
import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.admin.ui.entity.dto.UserAdminAndRoleDTO;
import com.silence.admin.ui.entity.dto.UserAdminDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public interface UserAdminService {


     String addUserAdmin(UserAdmin userAdmin);

   String updateUserAdmin(UserAdmin userAdmin) ;

   String deleteUserAdmin(Long id);

    List<String> findPermissionsByUserAdminId(Long id);

     UserAdmin getUserAdminByUserAdminName(String username) ;

    List<UserAdmin> userAdminList();

    String deleteRoleForUserAdmin(UserAdmin userAdmin);

   String addRoleForUserAdmin(UserAdmin userAdmin);

    @Transactional(rollbackFor = Exception.class)
   String setRoleForUserAdmin(UserAdmin userAdmin);
}
