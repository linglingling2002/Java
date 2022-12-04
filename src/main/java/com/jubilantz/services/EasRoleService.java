package com.jubilantz.services;

import com.jubilantz.entity.EasRole;

import java.util.List;

/**
 * 角色
 */
public interface EasRoleService {
    List<EasRole> getAll();

    String findRoleNameByRoleId(Integer roleid) throws Exception;
}
