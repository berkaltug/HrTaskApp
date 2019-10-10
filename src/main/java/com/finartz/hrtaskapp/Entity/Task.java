package com.finartz.hrtaskapp.Entity;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;



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
	
	private LinkedList<String> comments;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Task() {
	}


	public Task( @NotEmpty String title, @NotEmpty String body, String status, Integer priority,
			LinkedList<String> comments, User user) {
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

	public LinkedList<String> getComments() {
		return comments;
	}

	public void setComments(LinkedList<String> comments) {
		this.comments = comments;
	}	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Task [id=" + taskId + ", title=" + title + ", body=" + body + ", status=" + status + ", priority="
				+ priority + ", comments=" + comments + "]";
	}
	
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	
	
}
