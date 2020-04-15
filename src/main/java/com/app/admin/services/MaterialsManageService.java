package com.app.admin.services;

import com.app.admin.dto.MaterialsDTO;
import com.app.admin.model.Requirement.Requirement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MaterialsManageService {
    List<Requirement> MaterialsList();

    @Transactional
    Boolean sendRequirement(MaterialsDTO materialsDTO);

    @Transactional
    Boolean updateRequirement(MaterialsDTO materialsDTO);

}
