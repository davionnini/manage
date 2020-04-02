package com.app.admin.mapper;

import com.app.admin.model.User.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAll();

    long insert(User user);

    User getByNameAndPassword(String username, String password);

    User getByName(String username);

    Boolean updateByPrimaryKey(User user);


}
