package com.app.admin.controller;

import com.app.admin.dto.CommonDTO;
import com.app.admin.dto.MaterialsDTO;

import com.app.admin.dto.UserBindDTO;
import com.app.admin.services.MaterialsManageService;
import com.app.standard.common.CommonPage;
import com.app.standard.common.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求面板
 */
@RequestMapping("/materials")
@RestController
public class MaterialsManagerController {

    @Autowired
    MaterialsManageService materialsManageService;

    @RequestMapping("/list")
    public ReturnCode list(@RequestBody CommonDTO commonDTO)
    {
        return ReturnCode.success(CommonPage.restPage(materialsManageService.MaterialsList(commonDTO)));
    }

    @RequestMapping("/add")
    public ReturnCode create(@RequestBody MaterialsDTO materialsDTO)
    {
        materialsManageService.sendRequirement(materialsDTO);
        return ReturnCode.success();
    }

    @RequestMapping("/delete")
    public ReturnCode delete(@RequestParam long id)
    {
        return ReturnCode.success();
    }

    @RequestMapping("/update")
    public ReturnCode update(@RequestBody MaterialsDTO materialsDTO)
    {
        materialsManageService.updateRequirement(materialsDTO);
        return ReturnCode.success();
    }

    @RequestMapping("/bind")
    public ReturnCode bind(@RequestBody UserBindDTO userBindDTO)
    {
        return ReturnCode.success();
    }

    @RequestMapping("/bind/list")
    public ReturnCode bindList(@RequestBody CommonDTO commonDTO)
    {
        return ReturnCode.success(CommonPage.restPage(materialsManageService.userBindList(commonDTO)));
    }
}
