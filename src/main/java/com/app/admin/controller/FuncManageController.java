package com.app.admin.controller;

import com.app.admin.dto.CommonDTO;
import com.app.admin.dto.FuncDTO;
import com.app.admin.model.Func.Func;
import com.app.admin.services.PowerManageService;
import com.app.standard.common.CommonPage;
import com.app.standard.common.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/func")
@RestController
public class FuncManageController{

    @Autowired
    private PowerManageService powerManageService;

    @RequestMapping("/list")
    public ReturnCode<CommonPage<Func>> funcList(@RequestBody CommonDTO commonDTO)
    {
        List<Func> list = powerManageService.funcAll(commonDTO);

        return ReturnCode.success(CommonPage.restPage(list));
    }

    @RequestMapping("/add")
    public ReturnCode addFunc(@RequestBody FuncDTO func)
    {
        powerManageService.addFunc(func);
        return ReturnCode.success();
    }

    @RequestMapping("/all")
    public ReturnCode<CommonPage<Func>> funcList()
    {
        List<Func> list = powerManageService.funcAll();

        return ReturnCode.success(CommonPage.restPage(list));
    }
}
