package com.finartz.hrtaskapp.model.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
public class Task implements Cloneable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer taskId;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String body;
	
	@NotNull
	private String status; // statü nesnesi olarak değiştir.
	
	@NotNull
	private Integer priority;
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="task")
	@JsonIgnoreProperties("task")
	private List<Comment> comments=new LinkedList<Comment>();
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnoreProperties("tasks")
	private User user;
	
	
	public Task() {
	}

	public Task(Integer taskId, @NotEmpty String title, @NotEmpty String body, String status, Integer priority,
			List<Comment> comments, User user) {
		this.taskId = taskId;
		this.title = title;
		this.body = body;
		this.status = status;
		this.priority = priority;
		this.comments = comments;
		this.user = user;
	}

	public Integer getTaskId() {
		return taskId;
	}


	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", title=" + title + ", body=" + body + ", status=" + status + ", priority="
				+ priority + ", comments=" + comments ;
		// buradan userı çıkarttık 2 toString çakışıp sonsuz döngüyle stackoverflow verdi.
	}
	
	
	
}
