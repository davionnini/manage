package com.app.admin.services;

import com.app.admin.dto.FuncDTO;
import com.app.admin.dto.RoleDTO;
import com.app.admin.model.Func.Func;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PowerManageService {

    List<Func> funcAll();

    @Transactional
    Boolean addFunc(FuncDTO funcDTO);

    Boolean deleteFunc(long id);

    @Transactional
    Boolean addRole(RoleDTO roleDTO);

    Boolean deleteRole(long id);
}
