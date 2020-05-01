package com.app.admin.dao;

import com.app.admin.model.UserRequirementBind.UserRequirementBind;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRequireBindMapper {

    long insert(UserRequirementBind bind);

    UserRequirementBind getByRequireIdAndUserId(long requireId, long userId);

    List<UserRequirementBind> getByUserId(long userId);

}
