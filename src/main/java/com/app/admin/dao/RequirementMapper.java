package com.app.admin.dao;

import com.app.admin.model.Requirement.Requirement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequirementMapper {

    long insert(Requirement require);

    List<Requirement> getAllIgnoreBind();

    Boolean updateByPrimaryKey(Requirement require);

    Requirement getByRequireId(long requireId);
}
