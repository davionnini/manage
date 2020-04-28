package com.app.admin.dao;

import com.app.admin.model.Func.Func;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FuncMapper {

    List<Func> getAll();

    long insert(Func func);

    Func getById(long id);

    Boolean updateByPrimaryKey(Func func);

    Boolean delete(long id);

    List<Func> getByIdForRole(long roleId);

}
