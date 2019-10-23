package com.finartz.hrtaskapp.controllers;

import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
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
import org.springframework.web.bind.annotation.RestController;

import com.finartz.hrtaskapp.controllers.response.ResponseMessage;
import com.finartz.hrtaskapp.model.dto.CommentDto;
import com.finartz.hrtaskapp.model.dto.TaskDto;
import com.finartz.hrtaskapp.model.entity.Comment;
import com.finartz.hrtaskapp.model.entity.Task;
import com.finartz.hrtaskapp.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	private Logger logger= LoggerFactory.getLogger(TaskController.class);
	private TaskService taskService;
	private ModelMapper modelMapper;
	
	@Autowired
	public TaskController(TaskService taskService, ModelMapper modelMapper) {
		this.taskService = taskService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("{task_id}")
	public ResponseEntity<?>  getTask(@PathVariable("task_id") Integer taskId) {
		try {
			return new ResponseEntity<>(modelMapper.map(taskService.getTask(taskId),TaskDto.class),HttpStatus.OK);
		}catch (Exception e){
			logger.warn(e.getMessage());
			return new ResponseEntity<>(e.getCause().getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<String> addTask(@RequestBody TaskDto taskDTO) {
		Task task = modelMapper.map(taskDTO, Task.class);
		try {
			Task addedTask = taskService.addTask(task, taskDTO.getUserId());
			if (addedTask != null) {
				return new ResponseEntity<>(ResponseMessage.ADDED.get(), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(ResponseMessage.ADDINGERROR.get(), HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/{task_id}/edit")
	public ResponseEntity<String> updateTask(@PathVariable("task_id") Integer taskId, @RequestBody TaskDto newTask) {
		try {
			Task task = modelMapper.map(newTask, Task.class);
			Task responseTask = taskService.updateTask(task, taskId);
			if (responseTask != null) {
				return new ResponseEntity<>(ResponseMessage.UPDATED.get(), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(ResponseMessage.EDITERROR.get(), HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{task_id}/delete")
	public ResponseEntity<String> deleteTask(@PathVariable("task_id") Integer taskId){
		try {
			taskService.deleteTask(taskId);
			return new ResponseEntity<>(ResponseMessage.DELETED.get(),HttpStatus.OK);
		}catch(Exception e){
			logger.warn(e.getMessage());
			return new ResponseEntity<>(ResponseMessage.DELETEERROR.get(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/{task_id}/comment")
	public ResponseEntity<String> commentTask(@PathVariable("task_id") Integer taskId, @RequestBody CommentDto commentDTO) {
		try {
			Comment comment = modelMapper.map(commentDTO, Comment.class);
			taskService.commentTask(comment, taskId);
			return  new ResponseEntity<>(ResponseMessage.ADDED.get(),HttpStatus.CREATED);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ResponseEntity<>(ResponseMessage.ADDINGERROR.get(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
