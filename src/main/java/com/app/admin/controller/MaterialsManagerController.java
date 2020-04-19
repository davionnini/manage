package com.app.admin.controller;

import com.app.admin.dto.MaterialsDTO;

import com.app.admin.services.MaterialsManageService;
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

    @RequestMapping("/manager")
    public ReturnCode list(@RequestParam String token)
    {
        return ReturnCode.success(materialsManageService.MaterialsList());
    }

    @RequestMapping("/create")
    public Boolean create(@RequestBody MaterialsDTO materialsDTO)
    {
        materialsManageService.sendRequirement(materialsDTO);
        return true;
    }

    @RequestMapping("/delete")
    public Boolean delete(@RequestParam long id)
    {
        return true;
    }

    @RequestMapping("/update")
    public Boolean update(@RequestBody MaterialsDTO materialsDTO)
    {
        materialsManageService.updateRequirement(materialsDTO);
        return true;
    }
}
