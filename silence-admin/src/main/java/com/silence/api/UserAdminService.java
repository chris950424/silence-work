package com.silence.api;

import java.util.List;

/**
 * UserService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/23
 */
public interface UserAdminService {

    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    List<String> findPermissionsByUserAdminId(String id);

    /**
     * 查询用户权限
     *
     * @param username
     * @return
     */
    UserDto getUserAdminByUserAdminName(String username);
}
