package com.app.admin.dao;

import com.app.admin.model.UserRequirementBind.UserRequirementBind;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRequireBindMapper {

    long insert(UserRequirementBind bind);

}