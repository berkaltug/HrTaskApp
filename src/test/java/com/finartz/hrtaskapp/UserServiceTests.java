package com.finartz.hrtaskapp;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import com.finartz.hrtaskapp.model.entity.Role;
import com.finartz.hrtaskapp.model.entity.Task;
import com.finartz.hrtaskapp.model.entity.User;
import com.finartz.hrtaskapp.services.UserService;

@SpringBootTest
class UserServiceTests {
	
	@Autowired
	UserService userService;
	
	@Test
	void contextLoads() {
	}
	
//	@Test
//	@Order(2)
//	void isLoadAllUser() {
//		Pageable pageable = PageRequest.of(0,10,Sort.by("name"));
//		Assert.notNull(userService.getAllUsers(pageable));
//	}
	
	@Test
	@Order(1)
	void couldInsertUser() {
		List<Task> tasks = new ArrayList<Task>();
		List<Role> roles = new ArrayList<Role>();
		
		for(int i=0 ; i < 20 ; i++ ) {
			User u = new User("berk","altug","berkaltug", "asdsd", "berk@gmail.com", tasks,roles);
		}
		
	}
	
	@Test
	@Order(3)
	void couldGetUser() {
		Assert.notNull(userService.getUser(1));
	}
}
