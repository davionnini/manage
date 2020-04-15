package com.app.admin.services.imp;

import com.app.admin.dto.MaterialsDTO;
import com.app.admin.mapper.RequirementMapper;
import com.app.admin.mapper.UserRequireBindMapper;
import com.app.admin.model.Requirement.Requirement;
import com.app.admin.model.UserRequirementBind.UserRequirementBind;
import com.app.admin.services.MaterialsManageService;
import com.app.admin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialsManageServiceImp implements MaterialsManageService {

    @Autowired
    RequirementMapper requirement;

    @Autowired
    UserRequireBindMapper userRequireBindMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    /**
     * 获取需求列表
     * @return
     */
    public List<Requirement> MaterialsList()
    {
        return requirement.getAll();
    }

    /**
     * 发布需求
     * @param materialsDTO
     * @return
     */
    @Transactional
    public Boolean sendRequirement(MaterialsDTO materialsDTO)
    {
        Requirement requirementModel = new Requirement();

        UserRequirementBind bindModel = new UserRequirementBind();

        requirementModel.setCount(materialsDTO.getCount());
        requirementModel.setGoodName(materialsDTO.getGoodName());
        requirementModel.setDescribe(materialsDTO.getDescribe());
        requirementModel.setOrderId(System.currentTimeMillis());

        long requirementId = requirement.insert(requirementModel);
        long id = Integer.valueOf(jwtTokenUtil.getBodyField(materialsDTO.getToken(),"userId"));
        bindModel.setRequirementId(requirementId);
        bindModel.setUserId(id);

        userRequireBindMapper.insert(bindModel);

        return true;
    }

    /**
     * 修改需求基本信息
     * @param materialsDTO
     * @return
     */
    public Boolean updateRequirement(MaterialsDTO materialsDTO)
    {
        Requirement requirementModel = new Requirement();

        requirementModel.setId(materialsDTO.getId());
        requirementModel.setCount(materialsDTO.getCount());
        requirementModel.setGoodName(materialsDTO.getGoodName());
        requirementModel.setDescribe(materialsDTO.getDescribe());

        return requirement.updateByPrimaryKey(requirementModel);
    }

}
