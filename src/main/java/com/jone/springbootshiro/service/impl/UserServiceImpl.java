package com.jone.springbootshiro.service.impl;

import com.jone.springbootshiro.dao.UserMapper;
import com.jone.springbootshiro.model.User;
import com.jone.springbootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByName(String name) {
		return userMapper.findByName(name);
	}

	@Override
	public User findById(Integer id) {
		return userMapper.findById(id);
	}

	
}
