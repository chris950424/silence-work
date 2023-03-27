package com.silence.admin.infrastructure.repository;

import com.silence.admin.domain.authority.role.entity.Role;
import com.silence.admin.domain.authority.role.repository.RoleRepository;
import com.silence.admin.infrastructure.dao.RoleMapper;
import com.silence.admin.infrastructure.repository.DO.PermissionDO;
import com.silence.admin.infrastructure.repository.DO.RoleAndPermissionDO;
import com.silence.admin.infrastructure.repository.DO.RoleDO;
import com.silence.admin.infrastructure.repository.converter.RoleConverter;
import com.silence.admin.infrastructure.util.uid.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class RoleRepositoryImpl implements RoleRepository {
    @Resource
    private RoleMapper roleMapper;


    @Resource
    private UidGenerator generator;


    @Override
    public int savePermission(Role role) {
        RoleAndPermissionDO dO = new RoleAndPermissionDO();
        dO.setRoleId(role.getId());
        dO.setPermissionIds(role.getPermissionIds());
        return roleMapper.addPermissionForRole(dO);
    }


    @Override
    public int save(Role role) {
        RoleDO roleDO = RoleConverter.INSTANCE.roleToRoleDo(role);
        if (role.getId() != null) {
            return roleMapper.updateRole(roleDO);
        }
        roleDO.setId(generator.getUID());
        roleDO.setStatus(0);
        return roleMapper.addRole(roleDO);
    }

    @Override
    public int deleteRole(String id) {
        return roleMapper.deleteRole(id);

    }

    @Override
    public int removePermission(Role role) {
        RoleAndPermissionDO dO = new RoleAndPermissionDO();
        dO.setRoleId(role.getId());
        dO.setPermissionIds(role.getPermissionIds());
        return roleMapper.deletePermissionForRole(dO);
    }

    @Override
    public List<Role> findRole() {
        List<RoleDO> roleDos = roleMapper.roleList();
        List<Role> roles = new ArrayList<>();
        for (RoleDO dO : roleDos) {
            dO.setUrls(dO.getPermissionDos().stream().map(PermissionDO::getUrl).collect(Collectors.toList()));
            Role role = RoleConverter.INSTANCE.roleDOToRole(dO);
            roles.add(role);
        }
        return roles;
    }

    @Override
    public List<Long> findPermissionIds(Long id) {
        return roleMapper.getRolePermissionsByRoleId(id);
    }
}
