package com.jone.springbootshiro.service;


import com.jone.springbootshiro.model.User;

public interface UserService {

	public User findByName(String name);
	
	public User findById(Integer id);
}
