package com.finartz.hrtaskapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.hrtaskapp.Entity.Task;
import com.finartz.hrtaskapp.Repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public Task getTask(Integer id) {
		return taskRepository.getOne(id);
	}

	@Override
	public void addTask(Task task) {
		taskRepository.save(task);
		
	}

	@Override
	public void updateTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void deleteTask(Integer id) {
		taskRepository.delete(taskRepository.getOne(id));
	}

}
