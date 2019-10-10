package com.finartz.hrtaskapp;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.finartz.hrtaskapp.Entity.Role;
import com.finartz.hrtaskapp.Entity.Task;
import com.finartz.hrtaskapp.Entity.User;
import com.finartz.hrtaskapp.Services.UserService;

@SpringBootTest
class UserServiceTests {
	
	@Autowired
	UserService userService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void isLoadAllUser() {
		Assert.notNull(userService.getAllUsers());
	}
	
	@Test
	void couldInsertUser() {
		List<Task> tasks = new ArrayList<Task>();
		List<Role> roles = new ArrayList<Role>();
		
		User u = new User("berk","altug",tasks,roles);
		
		Assert.notNull(userService.addUser(u));
	}
	
	@Test
	void couldGetUser() {
		Assert.notNull(userService.getUser(1));
	}
}
