package com.app.admin.mapper;

import com.app.admin.model.Requirement.Requirement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequirementMapper {

    long insert(Requirement require);

    List<Requirement> getAll();

    Boolean updateByPrimaryKey(Requirement require);
}
