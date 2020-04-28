package com.app.admin.services.imp;

import com.app.admin.dao.FuncRoleMapper;
import com.app.admin.dto.CommonDTO;
import com.app.admin.dto.FuncDTO;
import com.app.admin.dto.RoleDTO;
import com.app.admin.dao.FuncMapper;
import com.app.admin.dao.RoleMapper;
import com.app.admin.model.Func.Func;
import com.app.admin.model.FuncRole.FuncRole;
import com.app.admin.model.Role.Role;
import com.app.admin.services.PowerManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PowerManageServiceImp implements PowerManageService {

    @Autowired
    private FuncMapper funcMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private FuncRoleMapper funcRoleMapper;

    /**
     * 功能列表
     * @return
     */
    public List<Func> funcAll()
    {
//        PageHelper.startPage(commonDTO.getPageNum(),commonDTO.getPageSize());
        return funcMapper.getAll();
    }


    /**
     * 菜单添加
     * @param funcDTO
     * @return
     */
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

    /**
     * 删除菜单
     * @param id
     * @return
     */
    public Boolean deleteFunc(long id)
    {
        funcMapper.delete(id);
        return true;
    }


    /**
     * 角色列表
     * @return
     */
    public List<Role> roleList(CommonDTO commonDTO)
    {
        PageHelper.startPage(commonDTO.getPageNum(),commonDTO.getPageSize());
        return roleMapper.getAllWithFunc();
    }

    @Transactional
    public Boolean addRole(RoleDTO roleDTO)
    {
        //插入角色
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        role.setRoleDesc(roleDTO.getDesc());
        roleMapper.insert(role);
        Long roleId = role.getId();

        //插入权限配置
        List<Long> funcIds = roleDTO.getFuncIds();

        List<FuncRole> insertList = new ArrayList<>();
        FuncRole insertData = new FuncRole();

        for (Long funcId:funcIds) {
            insertData.setRoleId(roleId);
            insertData.setFuncId(funcId);

            funcRoleMapper.insert(insertData);
        }

        return true;
    }

    @Transactional
    public Boolean updateRole(RoleDTO roleDTO)
    {

        List<Long> increasedFuncIds = new ArrayList<>();
        List<Long> lossFuncIds = new ArrayList<>();

        //更新
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        role.setRoleDesc(roleDTO.getDesc());
        role.setId(roleDTO.getId());
        Long roleId = roleDTO.getId();

        roleMapper.updateByPrimaryKey(role);

        //插入权限配置
        List<Long> funcIds = roleDTO.getFuncIds();

        //查询现有权限
        List<FuncRole> funcList = funcRoleMapper.getByRoleId(roleId);
        List<Long> existFuncIds = funcList.stream().map(FuncRole::getFuncId).collect(toList());
        System.out.println(existFuncIds);

        //转化
        existFuncIds = existFuncIds.parallelStream().collect(toList());
        funcIds = funcIds.parallelStream().collect(toList());

        //插入
        increasedFuncIds.addAll(funcIds);
        increasedFuncIds.removeAll(existFuncIds);


        FuncRole funcRole = new FuncRole();
        for (Long funcId:increasedFuncIds) {
            funcRole.setFuncId(funcId);
            funcRole.setRoleId(roleId);
            funcRoleMapper.insert(funcRole);
        }

        //删除
        lossFuncIds.addAll(existFuncIds);
        lossFuncIds.removeAll(funcIds);


        for (Long funcId:lossFuncIds) {
            funcRoleMapper.deleteByFuncIdAndRoleId(funcId,roleId);
        }

        return true;
    }


    /**
     * 删除角色
     * @param id
     * @return
     */
    public Boolean deleteRole(long id)
    {
        roleMapper.delete(id);
        return true;
    }
}
