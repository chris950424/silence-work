package com.silence.admin.infrastructure.repository;

import com.silence.admin.domain.authority.permission.entity.Permission;
import com.silence.admin.domain.authority.permission.repository.PermissionRepository;
import com.silence.admin.infrastructure.dao.PermissionMapper;
import com.silence.admin.infrastructure.repository.DO.PermissionDO;
import com.silence.admin.infrastructure.repository.converter.PermissionConverter;
import com.silence.admin.infrastructure.util.uid.UidGenerator;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * PermissionService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Repository
public class PermissionRepositoryImpl extends DbRepositorySupport implements PermissionRepository  {

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    UidGenerator generator;


    @Override
    public int save(Permission permission) {
        PermissionDO dO = PermissionConverter.INSTANCE.permissionToPermissionDO(permission);
        int i;
        if (dO.getId() != null) {
            i = permissionMapper.updatePermission(dO);
            return i;
        }
        dO.setId(generator.getUID());
        if (dO.getPid() == null){
            dO.setPid(0L);
        }
        i = permissionMapper.addPermission(dO);
        return i;
    }

    @Override
    public int remove(long id) {
        return permissionMapper.deletePermission(id);
    }

    @Override
    public List<Permission> find() {
        List<Permission> permissionList = new ArrayList<>();
        List<PermissionDO> permissionPoList = permissionMapper.getPermissionList();
        for (PermissionDO dO : permissionPoList) {
            permissionList.add(PermissionConverter.INSTANCE.permissionDOToPermission(dO));
        }
        return permissionList;
    }
}
