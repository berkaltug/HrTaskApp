package com.finartz.hrtaskapp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.hrtaskapp.Entity.Role;
import com.finartz.hrtaskapp.Entity.Task;
import com.finartz.hrtaskapp.Entity.User;
import com.finartz.hrtaskapp.Repository.RoleRepository;
import com.finartz.hrtaskapp.Services.UserService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/")
	public List<User> getAllUser(@RequestParam("page") int page){
		
		Pageable pageable=PageRequest.of(page, 10, Sort.by("name"));
		 return userService.getAllUsers(pageable).getContent();
		
	}
	
	@GetMapping("/{user_id}")
	public User getUser(@PathVariable("user_id") Integer userId) {
		
		return userService.getUser(userId);
	}
	
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		
		user.getRoles().add(roleRepository.findById(2).get());
		return userService.addUser(user);
	
	}
	
	@GetMapping("/{user_id}/tasks")
	public List<Task> getUserTasks(@PathVariable("user_id") Integer userId,@RequestParam("page") int page){
		
		//Unnecessary sending pageable object ??
		Pageable pageable=PageRequest.of(page, 5, Sort.by("priority"));
		User user=userService.getUser(userId);
		System.out.println(user);
		return user.getTasks(pageable); 
	
	}
	
	@GetMapping("/name/{name}")
	public List<User> getUsersLike(@PathVariable("name") String name){

		return userService.getUserByName(name);
		
	}
	
	@PostMapping("/login")
	ResponseEntity<String> login(){
		
		return new ResponseEntity<>("Başarılı",HttpStatus.ACCEPTED);
	}
}
