package com.silence.admin.infrastructure.dao;

import com.silence.admin.infrastructure.repository.DO.PermissionDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PermissionMapper
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Repository
public interface PermissionMapper {

    /**
     *
     */
    int addPermission(@Param("po") PermissionDO DO);

    int updatePermission(@Param("po") PermissionDO DO);

    int deletePermission(@Param("id") long id);


    List<PermissionDO> getPermissionList();
}
