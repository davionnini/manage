package com.app.admin.mapper;

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
}
