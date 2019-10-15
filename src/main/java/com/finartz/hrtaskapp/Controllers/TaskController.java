package com.finartz.hrtaskapp.Controllers;

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

import com.finartz.hrtaskapp.Entity.Task;
import com.finartz.hrtaskapp.Services.TaskService;
import com.finartz.hrtaskapp.Services.UserService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired 
	private UserService userService;
	
	@GetMapping("{task_id}")
	public Task getTask(@PathVariable("task_id") Integer taskId) {
		
		return taskService.getTask(taskId);
		
	}
	
	@PostMapping("/add")
	public Task addTask(@RequestBody Task task) {
		
		return taskService.addTask(task);
		
	}
	
	@PutMapping("/{task_id}/edit")
	public ResponseEntity<String> updateTask(@PathVariable("task_id") Integer taskId,@RequestBody Task newTask) {
		
		Task oldTask=taskService.getTask(taskId);
		//is modifying his own task ?
		if(userService.findLoggedInUsername().equals(oldTask.getUser().getUsername())) {
			
			oldTask=newTask;
			taskService.updateTask(oldTask);
			return new ResponseEntity<>("Update Successfull",HttpStatus.CREATED);
		
		}else {
			
			return new ResponseEntity<>("You can only modify your own tasks",HttpStatus.FORBIDDEN);
		}
			
	}
}
