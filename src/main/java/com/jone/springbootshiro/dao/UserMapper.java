package com.jone.springbootshiro.dao;

import com.jone.springbootshiro.model.User;

/**
 * @author Jone
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年09月14日 00:31
 */
public interface UserMapper {

    public User findByName(String name);

    public User findById(Integer id);
}
