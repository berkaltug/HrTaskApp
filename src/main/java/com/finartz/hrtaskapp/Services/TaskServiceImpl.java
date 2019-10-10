package com.finartz.hrtaskapp.Services;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.transaction.RollbackException;

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
		return taskRepository.findById(id).get();
	}

	@Override
	public Task addTask(Task task) {
		try {
			return taskRepository.save(task);
		}catch(Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
	//Persistence Exceptionları ayrı yakalamaya gerek var mı bak !
	@Override
	public Task updateTask(Task task) {
		try {
		return taskRepository.save(task);
		}catch(PersistenceException e) {
			System.err.println(e.getMessage());
			return null;
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public int deleteTask(Integer id) {
		try {
			taskRepository.delete(taskRepository.getOne(id));
			return 1;
		}catch(EntityNotFoundException e) {
			System.err.println(e.getMessage());
			return 0;
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

}
