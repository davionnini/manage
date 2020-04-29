package com.app.admin.dao;

import com.app.admin.model.Func.Func;
import com.app.admin.model.User.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAll();

    long insert(User user);

    User getByNameAndPassword(String username, String password);

    User getByName(String username);

    User getById(long id);

    Boolean updateByPrimaryKey(User user);

    List<Func> funcList(long userId);

    Boolean deleteById(Long id);

    List<User> getByLikeName(User user);


}
