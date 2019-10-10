package com.finartz.hrtaskapp.Services;

import java.util.List;

import com.finartz.hrtaskapp.Entity.User;

public interface UserService {
	
	List<User> getAllUsers();
	User getUser(Integer id);
	User getUserByName(String name);
	User addUser(User user);
}
