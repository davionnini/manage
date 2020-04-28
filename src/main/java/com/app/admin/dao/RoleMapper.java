package com.app.admin.dao;

import com.app.admin.model.Role.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> getAll();

    long insert(Role role);

    Role getById(long id);

    Boolean updateByPrimaryKey(Role role);

    Boolean delete(long id);

    List<Role> getByIds(List roleIds);

    List<Role> getByUserId(long userId);

    List<Role> getAllWithFunc();
}
