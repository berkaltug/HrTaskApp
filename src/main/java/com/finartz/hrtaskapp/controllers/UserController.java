package com.finartz.hrtaskapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import com.finartz.hrtaskapp.controllers.response.ResponseMessage;
import com.finartz.hrtaskapp.model.dto.TaskDTO;
import com.finartz.hrtaskapp.model.dto.UserDTO;
import com.finartz.hrtaskapp.model.entity.User;
import com.finartz.hrtaskapp.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private ModelMapper modelMapper;
	
	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/")
	public List<UserDTO> getAllUser(@RequestParam("page") int page){
		
		Pageable pageable=PageRequest.of(page, 10, Sort.by("name"));
		//mapping all users to UserDTO
		List<UserDTO> allUsers=userService
				.getAllUsers(pageable)
				.getContent()
				.stream()
				.map(user -> modelMapper.map(user,UserDTO.class))
				.collect(Collectors.toList());
		return allUsers;
	}
	
	@GetMapping("/{user_id}")
	public UserDTO getUser(@PathVariable("user_id") Integer userId) {
		return modelMapper.map(userService.getUser(userId),UserDTO.class);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
		 userService.addUser(modelMapper.map(userDTO, User.class));
		 return new ResponseEntity<>(ResponseMessage.ADDED.get(),HttpStatus.CREATED);
	}
	
	@GetMapping("/{user_id}/tasks")
	public List<TaskDTO> getUserTasks(@PathVariable("user_id") Integer userId,@RequestParam("page") int page){
		
		//Unnecessary sending pageable object ??
		Pageable pageable=PageRequest.of(page, 5, Sort.by("priority"));
		User user=userService.getUser(userId);
		System.out.println(user);
		return user
				.getTasks(pageable)
				.stream()
				.map(task -> modelMapper.map(task, TaskDTO.class))
				.collect(Collectors.toList()); 
	
	}
	
	@GetMapping("/name/{name}")
	public List<UserDTO> getUsersLike(@PathVariable("name") String name){

		return userService.getUserByName(name)
				.stream()
				.map(user -> modelMapper.map( user , UserDTO.class))
				.collect(Collectors.toList());
		
	}
	
	@PostMapping("/login")
	ResponseEntity<String> login(){
		
		return new ResponseEntity<>(ResponseMessage.LOGGEDIN.get(),HttpStatus.ACCEPTED);
	}
}
