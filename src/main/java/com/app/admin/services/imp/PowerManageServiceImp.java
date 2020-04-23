package com.app.admin.services.imp;

import com.app.admin.dto.FuncDTO;
import com.app.admin.dto.RoleDTO;
import com.app.admin.mapper.FuncMapper;
import com.app.admin.mapper.RoleMapper;
import com.app.admin.model.Func.Func;
import com.app.admin.model.Role.Role;
import com.app.admin.services.PowerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PowerManageServiceImp implements PowerManageService {

    @Autowired
    private FuncMapper funcMapper;

    @Autowired
    private RoleMapper roleMapper;

    public List<Func> funcAll()
    {

        return funcMapper.getAll();
    }

    @Transactional
    public Boolean addFunc(FuncDTO funcDTO)
    {
        Func func = new Func();
        func.setFuncName(funcDTO.getFuncName());
        func.setFuncDesc(funcDTO.getDesc());
        func.setFuncUrl(funcDTO.getFuncUrl());
        funcMapper.insert(func);
        return true;
    }

    public Boolean deleteFunc(long id)
    {
        funcMapper.delete(id);
        return true;
    }

    @Transactional
    public Boolean addRole(RoleDTO roleDTO)
    {
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        role.setRoleDesc(roleDTO.getDesc());
        roleMapper.insert(role);
        return true;
    }

    public Boolean deleteRole(long id)
    {
        roleMapper.delete(id);
        return true;
    }
}
