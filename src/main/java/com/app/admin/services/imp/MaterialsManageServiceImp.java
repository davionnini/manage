package com.app.admin.services.imp;

import com.app.admin.dto.CommonDTO;
import com.app.admin.dto.MaterialsDTO;
import com.app.admin.dao.RequirementMapper;
import com.app.admin.dao.UserRequireBindMapper;
import com.app.admin.dto.UserBindDTO;
import com.app.admin.model.Requirement.Requirement;
import com.app.admin.model.UserRequirementBind.UserRequirementBind;
import com.app.admin.services.MaterialsManageService;
import com.app.admin.utils.JwtTokenUtil;
import com.app.admin.utils.UniqueIdUtil;
import com.github.pagehelper.PageHelper;
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
    public List<Requirement> MaterialsList(CommonDTO commonDTO)
    {
        PageHelper.startPage(commonDTO.getPageNum(),commonDTO.getPageSize());
        return requirement.getAllIgnoreBind();
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

//        UserRequirementBind bindModel = new UserRequirementBind();

        long userId = Integer.valueOf(jwtTokenUtil.getBodyField(materialsDTO.getToken(),"userId"));


        requirementModel.setCreatorId(userId);
        requirementModel.setCount(materialsDTO.getCount());
        requirementModel.setGoodName(materialsDTO.getGoodName());
        requirementModel.setDescribe(materialsDTO.getDescribe());
        requirementModel.setOrderId(UniqueIdUtil.nextId());
        requirement.insert(requirementModel);


//        bindModel.setRequirementId(requirementModel.getId());
//        bindModel.setUserId(userId);
//        userRequireBindMapper.insert(bindModel);

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

    /**
     * 需求绑定
     * @param userBindDTO
     * @return
     */
    public Boolean bind(UserBindDTO userBindDTO)
    {
        long userId = Integer.valueOf(jwtTokenUtil.getBodyField(userBindDTO.getToken(),"userId"));
        long requireId = userBindDTO.getRequirementId();

        //判断是否绑定
        UserRequirementBind exist = userRequireBindMapper.getByRequireIdAndUserId(requireId,userId);
        if(exist == null){
            UserRequirementBind bindModel = new UserRequirementBind();
            bindModel.setUserId(userId);
            bindModel.setRequirementId(requireId);
            userRequireBindMapper.insert(bindModel);
        }
        return true;
    }

    /**
     * 已绑定的需求
     * @param commonDTO
     * @return
     */
    public List<UserRequirementBind> userBindList(CommonDTO commonDTO)
    {
        long userId = Integer.valueOf(jwtTokenUtil.getBodyField(commonDTO.getToken(),"userId"));
        PageHelper.startPage(commonDTO.getPageNum(),commonDTO.getPageSize());
        return userRequireBindMapper.getByUserId(userId);
    }

}
