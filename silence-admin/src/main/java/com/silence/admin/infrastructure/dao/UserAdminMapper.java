package com.silence.admin.infrastructure.dao;

import com.silence.admin.infrastructure.repository.DO.UserAdminDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Repository
public interface UserAdminMapper {

    /**
     * 查找管理人员通过管理人员姓名
     *
     * @param username
     * @return
     */
    UserAdminDO getUserAdminByUserAdminName(String username);


    /**
     * 查找管理人员通过用户id
     *
     * @param id
     * @return
     */
    List<String> findPermissionsByUserAdminId(Long id);

    /**
     * 添加管理人员
     *
     * @param dO
     * @return
     */
    int addUserAdmin(UserAdminDO dO);

    /**
     * 更新管理人员
     *
     * @param dO
     * @return
     */
    int updateUserAdmin(UserAdminDO dO);

    /**
     * 删除管理人员
     *
     * @param id
     * @return
     */
    int deleteUserAdmin(Long id);

    List<UserAdminDO> userAdminList();

    int deleteRoleForUserAdmin(UserAdminDO adminPo);

    int addRoleForUserAdmin(UserAdminDO adminPo);

    List<Long> getRoleForUserAdmin(Long userId);
}
