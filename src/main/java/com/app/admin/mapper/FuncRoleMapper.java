package com.app.admin.mapper;

import com.app.admin.model.FuncRole.FuncRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FuncRoleMapper {

    List<FuncRole> getAll();

    Boolean insert(FuncRole funcRole);

    Boolean updateByPrimaryKey(FuncRole funcRole);

    Boolean delete(long id);

}
