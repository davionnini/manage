package com.app.admin.services;

import com.app.admin.dto.CommonDTO;
import com.app.admin.dto.MaterialsDTO;
import com.app.admin.dto.UserBindDTO;
import com.app.admin.model.Requirement.Requirement;
import com.app.admin.model.UserRequirementBind.UserRequirementBind;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MaterialsManageService {
    List<Requirement> MaterialsList(CommonDTO commonDTO);

    @Transactional
    Boolean sendRequirement(MaterialsDTO materialsDTO);

    @Transactional
    Boolean updateRequirement(MaterialsDTO materialsDTO);

    Boolean bind(UserBindDTO userBindDTO);

    List<UserRequirementBind> userBindList(CommonDTO commonDTO);

}
