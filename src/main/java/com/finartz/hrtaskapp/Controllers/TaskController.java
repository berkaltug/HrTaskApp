package com.finartz.hrtaskapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.hrtaskapp.Entity.Task;
import com.finartz.hrtaskapp.Services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("{task_id}")
	public Task getTask(@PathVariable("task_id") Integer taskId) {
		return taskService.getTask(taskId);
	}
	
	@PostMapping("/add")
	public Task addTask(@RequestBody Task task) {
		return taskService.addTask(task);
	}
	
	@PutMapping("/{task_id}/edit")
	public Task updateTask(@PathVariable("task_id") Integer taskId,@RequestBody Task newTask) {
//		Task oldTask=taskService.getTask(taskId);
//		oldTask=newTask;
		return taskService.updateTask(newTask);
	}
}
