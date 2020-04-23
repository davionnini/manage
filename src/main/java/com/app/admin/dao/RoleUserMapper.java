package com.app.admin.dao;

import com.app.admin.model.RoleUser.RoleUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleUserMapper {


    List<RoleUser> getAll();

    Boolean insert(RoleUser roleUser);

    Boolean updateByPrimaryKey(RoleUser roleUser);

    Boolean delete(long id);


}
