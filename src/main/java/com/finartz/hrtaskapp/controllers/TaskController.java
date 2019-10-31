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

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	private TaskService taskService;
	private ModelMapper modelMapper;

	@Autowired
	public TaskController(TaskService taskService, ModelMapper modelMapper) {
		this.taskService = taskService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/{task_id}")
	public ResponseEntity<?> getTask(@PathVariable("task_id") Integer taskId) {
		Optional<Task> optionalTask = taskService.getTask(taskId);
		if (!optionalTask.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.NOVALUE.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(modelMapper.map(optionalTask.get(), TaskDto.class), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addTask(@RequestBody TaskDto taskDTO) {
		Optional<Task> addedTask = taskService.addTask(modelMapper.map(taskDTO, Task.class), taskDTO.getUserId(), taskDTO.getOwnerProcessId());
		if (!addedTask.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.ADDINGERROR.get(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(ResponseMessage.ADDED.get(), HttpStatus.OK);
	}


	@PutMapping("/{task_id}/edit")
	public ResponseEntity<String> updateTask(@PathVariable("task_id") Integer taskId, @RequestBody TaskDto newTask) {
		//not the perfect way mapping creationDate
		taskService.getTask(taskId).ifPresent(t->newTask.setCreationDate(t.getCreationDate()));
		Optional<Task> responseTask = taskService.updateTask(modelMapper.map(newTask, Task.class), taskId);
		if (!responseTask.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.EDITERROR.get(), HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(ResponseMessage.UPDATED.get(), HttpStatus.CREATED);
	}

	@DeleteMapping("/{task_id}/delete")
	public ResponseEntity<String> deleteTask(@PathVariable("task_id") Integer taskId) {
		Optional<Task> deletedTask = taskService.deleteTask(taskId);
		if (!deletedTask.isPresent())
			return new ResponseEntity<>(ResponseMessage.DELETEERROR.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(ResponseMessage.DELETED.get(), HttpStatus.OK);

	}


	@PostMapping("/{task_id}/comment")
	public ResponseEntity<String> commentTask(@PathVariable("task_id") Integer taskId, @RequestBody CommentDto commentDTO) {
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		Optional<Comment> commentOptional = taskService.commentTask(comment, taskId);
		if (!commentOptional.isPresent())
			return new ResponseEntity<>(ResponseMessage.ADDINGERROR.get(), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(ResponseMessage.ADDED.get(), HttpStatus.CREATED);
	}
}
