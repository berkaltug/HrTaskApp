package com.finartz.hrtaskapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.hrtaskapp.controllers.response.ResponseMessage;
import com.finartz.hrtaskapp.model.dto.TaskDTO;
import com.finartz.hrtaskapp.model.entity.Task;
import com.finartz.hrtaskapp.services.TaskService;
import com.finartz.hrtaskapp.services.UserService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	private TaskService taskService;
	private UserService userService;
	private ModelMapper modelMapper;
	
	@Autowired
	public TaskController(TaskService taskService, UserService userService, ModelMapper modelMapper) {
		this.taskService = taskService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	 
	@GetMapping("{task_id}")
	public TaskDTO getTask(@PathVariable("task_id") Integer taskId) {
		Task task=taskService.getTask(taskId);
		TaskDTO taskDTO=modelMapper.map(task, TaskDTO.class);
		return taskDTO;
		
	}
	
	@PostMapping("/add")
	public Task addTask(@RequestBody TaskDTO taskDTO) {
		Task task = modelMapper.map(taskDTO, Task.class);
		return taskService.addTask(task);
		
	}
	
	//bunu fonksiyonu yeniden incele
	@PutMapping("/{task_id}/edit")
	public ResponseEntity<String> updateTask(@PathVariable("task_id") Integer taskId,@RequestBody TaskDTO newTask) {
		
		Task oldTask=taskService.getTask(taskId);
		//is modifying his own task ?
		if(userService.findLoggedInUsername().equals(oldTask.getUser().getUsername())) {
			Task task=modelMapper.map(newTask,Task.class);
			oldTask=task;
			taskService.updateTask(oldTask);
			return new ResponseEntity<>(ResponseMessage.UPDATED.get(),HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(ResponseMessage.EDITERROR.get(),HttpStatus.FORBIDDEN);
		}
			
	}
}
