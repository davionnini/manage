package com.app.admin.controller;

import com.app.admin.dao.FuncRoleMapper;
import com.app.admin.dto.CommonDTO;
import com.app.admin.dto.FuncDTO;
import com.app.admin.dto.RoleDTO;
import com.app.admin.model.FuncRole.FuncRole;
import com.app.admin.model.Role.Role;
import com.app.admin.services.PowerManageService;
import com.app.standard.common.CommonPage;
import com.app.standard.common.ReturnCode;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleManageController {

    @Autowired
    private PowerManageService powerManageService;



    @RequestMapping("/add")
    public ReturnCode add(@RequestBody RoleDTO roleDTO)
    {
        powerManageService.addRole(roleDTO);


        return ReturnCode.success();
    }

    @RequestMapping("/modify")
    public ReturnCode modify(@RequestBody RoleDTO roleDTO)
    {
        powerManageService.updateRole(roleDTO);

        return ReturnCode.success();
    }

    @RequestMapping("/delete")
    public ReturnCode delete(@RequestBody RoleDTO role)
    {
        powerManageService.deleteRole(role.getId());
        return ReturnCode.success();
    }

    @RequestMapping("/list")
    public ReturnCode<CommonPage<Role>> list(@RequestBody CommonDTO commonDTO)
    {
        List<Role> roles = powerManageService.roleList(commonDTO);
        return ReturnCode.success(CommonPage.restPage(roles));
    }

}
