package com.finartz.hrtaskapp.model.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
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

	@Temporal(TemporalType.DATE)
	@NotEmpty
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	private Date updateDate;

	@Temporal(TemporalType.DATE)
	private Date closeDate;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="task")
	private List<Comment> comments=new LinkedList<Comment>();
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Task() {
	}

	public Task(Integer taskId, @NotEmpty String title, @NotEmpty String body, String status, Integer priority,
			List<Comment> comments, User user,Date creationDate,Date updateDate,Date closeDate) {
		this.taskId = taskId;
		this.title = title;
		this.body = body;
		this.status = status;
		this.priority = priority;
		this.comments = comments;
		this.user = user;
		this.creationDate= creationDate;
		this.updateDate=updateDate;
		this.closeDate=closeDate;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Task)) return false;
		Task task = (Task) o;
		return Objects.equals(taskId, task.taskId) &&
				Objects.equals(title, task.title) &&
				Objects.equals(body, task.body) &&
				Objects.equals(status, task.status) &&
				Objects.equals(priority, task.priority) &&
				Objects.equals(creationDate, task.creationDate) &&
				Objects.equals(updateDate, task.updateDate) &&
				Objects.equals(closeDate, task.closeDate) &&
				Objects.equals(comments, task.comments) &&
				Objects.equals(user, task.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(taskId, title, body, status, priority, creationDate, updateDate, closeDate, comments, user);
	}

	@Override
	public String toString() {
		return "Task{" +
				"taskId=" + taskId +
				", title='" + title + '\'' +
				", body='" + body + '\'' +
				", status='" + status + '\'' +
				", priority=" + priority +
				", creationDate=" + creationDate +
				", updateDate=" + updateDate +
				", closeDate=" + closeDate +
				", comments=" + comments +
				", user=" + user +
				'}';
	}
}
