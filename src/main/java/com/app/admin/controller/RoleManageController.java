package com.app.admin.controller;

import com.app.admin.dto.FuncDTO;
import com.app.admin.dto.RoleDTO;
import com.app.admin.services.PowerManageService;
import com.app.standard.common.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/role")
@RestController
public class RoleManageController {

    @Autowired
    private PowerManageService powerManageService;

    @RequestMapping("/add")
    public ReturnCode addRole(@RequestBody RoleDTO roleDTO)
    {
        powerManageService.addRole(roleDTO);
        return ReturnCode.success();
    }

    public ReturnCode deleteRole(@RequestBody RoleDTO role)
    {
        powerManageService.deleteRole(role.getId());
        return ReturnCode.success();
    }

    public ReturnCode roleList(@RequestBody FuncDTO func)
    {
        powerManageService.addFunc(func);
        return ReturnCode.success();
    }

}
