package com.silence.admin.ui.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * RoleVo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Data
public class RoleAndPermissionDTO {
    private String id;
    private List<String> permissions;
}
