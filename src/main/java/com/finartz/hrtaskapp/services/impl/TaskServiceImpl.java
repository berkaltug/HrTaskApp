package com.finartz.hrtaskapp.services.impl;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import com.finartz.hrtaskapp.model.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.hrtaskapp.db.repository.CommentRepository;
import com.finartz.hrtaskapp.db.repository.TaskRepository;
import com.finartz.hrtaskapp.model.entity.Comment;
import com.finartz.hrtaskapp.model.entity.Task;
import com.finartz.hrtaskapp.services.TaskService;
import com.finartz.hrtaskapp.services.UserService;

import java.util.Date;


@Service
public class TaskServiceImpl implements TaskService{

	Logger logger=LoggerFactory.getLogger(TaskServiceImpl.class);
	
	private UserService userService;
	private TaskRepository taskRepository;
	private CommentRepository commentRepository;
	
	@Autowired
	public TaskServiceImpl(UserService userService, TaskRepository taskRepository,CommentRepository commentRepository) {
		this.userService = userService;
		this.taskRepository = taskRepository;
		this.commentRepository = commentRepository;
	}

	@Override
	public Task getTask(Integer id) throws Exception {
		return taskRepository.findById(id).get();
	}

	@Override
	public Task addTask(Task task,Integer userId) throws Exception{
		
		task.setUser(userService.getUser(userId));
		task.setCreationDate(new Date());
		if(task.getUser()!=null) {
			return taskRepository.save(task);
		}else {
			return null;
		}	
	}
	
	//Persistence Exceptionları ayrı yakalamaya gerek var mı bak !
	@Override
	public Task updateTask(Task task,Integer taskId) throws Exception {
			Task oldTask=taskRepository.findById(taskId).get();
			//DTO'nun içinde id olmadığı için id set ediyoruz ki düzgün güncellesin
			task.setTaskId(oldTask.getTaskId());
			task.setUpdateDate(new Date());
			//done yapıldıysa kapama tarihi ata
			if(task.getStatus()== TaskStatus.DONE.get()){
				task.setCloseDate(new Date());
			}
			//kendi taskını mı güncelliyor ?
			if(	userService.findLoggedInUsername().equals(task.getUser().getUsername())
					||
					userService.isAdmin())
				 {
				return taskRepository.save(task);
			}else {
				return null;
			}
	}

	@Override
	public void deleteTask(Integer id) throws Exception{
		taskRepository.delete(taskRepository.findById(id).get());
	}

	@Override
	public Comment commentTask(Comment comment,Integer taskId) throws Exception {
		Task task = taskRepository.findById(taskId).get();
		comment.setSender(userService.findLoggedInUsername());
		comment.setTask(task);
		return commentRepository.save(comment);
	}
}
