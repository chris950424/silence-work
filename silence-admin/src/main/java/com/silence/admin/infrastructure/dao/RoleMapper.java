package com.silence.admin.infrastructure.dao;

import com.silence.admin.infrastructure.repository.DO.RoleAndPermissionDO;
import com.silence.admin.infrastructure.repository.DO.RoleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleMapper
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Repository
public interface RoleMapper {

    /**
     * @param po
     */
    int addPermissionForRole(RoleAndPermissionDO po);

    int updateRole(RoleDO po);

    int addRole(RoleDO po);

    int deleteRole(String id);

    int deletePermissionForRole(RoleAndPermissionDO po);

    List<RoleDO> roleList();

    List<Long> getRolePermissionsByRoleId(@Param("id") Long id);
}
