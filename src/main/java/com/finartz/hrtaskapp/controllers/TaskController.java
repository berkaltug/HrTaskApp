package com.finartz.hrtaskapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.hrtaskapp.controllers.response.ResponseMessage;
import com.finartz.hrtaskapp.model.dto.CommentDTO;
import com.finartz.hrtaskapp.model.dto.TaskDTO;
import com.finartz.hrtaskapp.model.entity.Comment;
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
	public ResponseEntity<String> addTask(@RequestBody TaskDTO taskDTO) {
		Task task = modelMapper.map(taskDTO, Task.class);
		task.setUser(userService.getUser(taskDTO.getUserId()));
		Task addedTask = taskService.addTask(task);
		if(addedTask!=null) {
			return new ResponseEntity<>(ResponseMessage.ADDED.get(),HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(ResponseMessage.ADDINGERROR.get(),HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@PutMapping("/{task_id}/edit")
	public ResponseEntity<String> updateTask(@PathVariable("task_id") Integer taskId,@RequestBody TaskDTO newTask) {
			Task task=modelMapper.map(newTask,Task.class);
			Task responseTask=taskService.updateTask(task,taskId);
		if(responseTask!=null) {
			return new ResponseEntity<>(ResponseMessage.UPDATED.get(),HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(ResponseMessage.EDITERROR.get(),HttpStatus.FORBIDDEN);
		}
	}
	
	@DeleteMapping("/{task_id}/delete")
	public ResponseEntity<String> deleteTask(@PathVariable("task_id") Integer taskId){
		int status=taskService.deleteTask(taskId);
		if(status==1) {
			return new ResponseEntity<String>(ResponseMessage.DELETED.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(ResponseMessage.DELETEERROR.get(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/{task_id}/comment")
	public ResponseEntity<String> commentTask(@PathVariable("task_id")Integer taskId,@RequestBody CommentDTO commentDTO){
		Comment comment=modelMapper.map(commentDTO, Comment.class);
		int status=taskService.commentTask(comment,taskId);
		if(status==1) {
			return new ResponseEntity<>(ResponseMessage.ADDED.get(),HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(ResponseMessage.ADDINGERROR.get(),HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
}
