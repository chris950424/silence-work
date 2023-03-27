package com.silence.admin.infrastructure.repository;

import com.silence.admin.domain.authority.useradmin.entity.UserAdmin;
import com.silence.admin.domain.authority.useradmin.repository.UserAdminRepository;
import com.silence.admin.infrastructure.dao.UserAdminMapper;
import com.silence.admin.infrastructure.repository.DO.UserAdminDO;
import com.silence.admin.infrastructure.repository.converter.UserAdminConverter;
import com.silence.admin.infrastructure.util.uid.UidGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Repository
public class UserAdminRepositoryImpl implements UserAdminRepository {
    @Resource
    private UserAdminMapper userMapper;

    @Resource
    PasswordEncoder encoder;

    @Resource
    UidGenerator generator;

    @Override
    public int save(UserAdmin vo) {
        UserAdminDO userAdminDO = UserAdminConverter.INSTANCE.userAdminToUserAdminDO(vo);
        if(vo.getId()!=null){
            userAdminDO.setPassword(encoder.encode(userAdminDO.getPassword()));
            return userMapper.updateUserAdmin(userAdminDO);
        }
        userAdminDO.setId(generator.getUID());
        userAdminDO.setPassword(encoder.encode(userAdminDO.getPassword()));
        return userMapper.addUserAdmin(userAdminDO);
    }

    @Override
    public int remove(Long id) {
        return userMapper.deleteUserAdmin(id);
    }


    @Override
    public List<String> findPermissionsByUserAdminId(Long id) {
        return userMapper.findPermissionsByUserAdminId(id);
    }

    @Override
    public UserAdmin findUserAdminByUserAdminName(String username) {
        UserAdminDO dO = userMapper.getUserAdminByUserAdminName(username);
        return UserAdminConverter.INSTANCE.userAdminDOToUserAdmin(dO);
    }

    @Override
    public List<UserAdmin> find() {
        List<UserAdminDO> userAdminPos = userMapper.userAdminList();
        return   UserAdminConverter.INSTANCE.userAdminPosToUserAdmins(userAdminPos);
    }

    @Override
    public int removeRoleForUserAdmin(UserAdmin admin) {
        UserAdminDO userAdminDO = UserAdminConverter.INSTANCE.userAdminToUserAdminDO(admin);
        return userMapper.deleteRoleForUserAdmin(userAdminDO);
    }

    @Override
    public int saveRoleForUserAdmin(UserAdmin admin) {
        UserAdminDO userAdminDO = UserAdminConverter.INSTANCE.userAdminToUserAdminDO(admin);
        return userMapper.addRoleForUserAdmin(userAdminDO);
    }

    @Override
    public List<Long> findRoleForUserAdmin(Long userId) {
        return userMapper.getRoleForUserAdmin(userId);
    }
}
