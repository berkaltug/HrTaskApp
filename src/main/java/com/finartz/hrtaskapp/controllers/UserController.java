package com.finartz.hrtaskapp.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finartz.hrtaskapp.controllers.response.ResponseMessage;
import com.finartz.hrtaskapp.model.dto.TaskDto;
import com.finartz.hrtaskapp.model.dto.UserDto;
import com.finartz.hrtaskapp.model.entity.User;
import com.finartz.hrtaskapp.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	private ModelMapper modelMapper;

	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/")
	public Page<UserDto> getAllUser(@RequestParam("page") int page) {
		return userService.getAllUsers(page);

	}

	@GetMapping("/{user_id}")
	public ResponseEntity<?> getUser(@PathVariable("user_id") Integer userId) {
		Optional<User> user=userService.getUser(userId);
		if(!user.isPresent()){
			return new ResponseEntity<>(ResponseMessage.NOVALUE.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(modelMapper.map(user.get(),UserDto.class), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody UserDto userDTO) {
		try {
			userService.addUser(modelMapper.map(userDTO, User.class));
			return new ResponseEntity<>(ResponseMessage.ADDED.get(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(ResponseMessage.ADDINGERROR.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{user_id}/tasks")
	public ResponseEntity<?> getUserTasks(@PathVariable("user_id") Integer userId, @RequestParam("page") int page) {
		try {
			//Unnecessary sending pageable object ??
			Pageable pageable = PageRequest.of(page, 5, Sort.by("priority"));
			Optional<User> user = userService.getUser(userId);
			if (user.isPresent()) {
				List<TaskDto> tasks = user.get()
						.getTasks(pageable)
						.stream()
						.map(task -> modelMapper.map(task, TaskDto.class))
						.collect(Collectors.toList());
				return new ResponseEntity<>(tasks, HttpStatus.OK);
			}
			return new ResponseEntity<>(ResponseMessage.NOVALUE.get(),HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/name/{name}")
	public List<UserDto> getUsersLike(@PathVariable("name") String name) {

		return userService.getUserByName(name)
				.stream()
				.map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());

	}

	@PostMapping("/login")
	public ResponseEntity<String> login() {
		return new ResponseEntity<>(ResponseMessage.LOGGEDIN.get(), HttpStatus.ACCEPTED);
	}
}
