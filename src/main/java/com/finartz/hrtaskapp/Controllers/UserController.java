package com.finartz.hrtaskapp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.hrtaskapp.Entity.Task;
import com.finartz.hrtaskapp.Entity.User;
import com.finartz.hrtaskapp.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	
	@GetMapping("/")
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{user_id}")
	public User getUser(@PathVariable("user_id") Integer userId) {
		return userService.getUser(userId);
	}
	
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@GetMapping("/{user_id}/tasks")
	public List<Task> getUserTasks(@PathVariable("user_id") Integer userId){
		return userService.getUser(userId).getTasks(); // null dönebilir fetch lazy dedik
	}
	
	
}
