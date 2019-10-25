package com.finartz.hrtaskapp.model.dto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TaskDto {
	
	private String title;
	private String body;
	private String status;
	private Integer priority;
	private Date creationDate;
	private Date updateDate;
	private Date closeDate;
	private List<CommentDto> comments=new LinkedList<CommentDto>();
	private Integer userId;
	private Integer ownerProcessId;

	public TaskDto() {
	}

	public TaskDto(String title, String body, String status, Integer priority, Date creationDate, Date updateDate, Date closeDate, List<CommentDto> comments, Integer userId, Integer ownerProcessId) {
		this.title = title;
		this.body = body;
		this.status = status;
		this.priority = priority;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.closeDate = closeDate;
		this.comments = comments;
		this.userId = userId;
		this.ownerProcessId = ownerProcessId;
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

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getOwnerProcessId() {
		return ownerProcessId;
	}

	public void setOwnerProcessId(Integer ownerProcessId) {
		this.ownerProcessId = ownerProcessId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TaskDto)) return false;
		TaskDto taskDto = (TaskDto) o;
		return Objects.equals(title, taskDto.title) &&
				Objects.equals(body, taskDto.body) &&
				Objects.equals(status, taskDto.status) &&
				Objects.equals(priority, taskDto.priority) &&
				Objects.equals(creationDate, taskDto.creationDate) &&
				Objects.equals(updateDate, taskDto.updateDate) &&
				Objects.equals(closeDate, taskDto.closeDate) &&
				Objects.equals(comments, taskDto.comments) &&
				Objects.equals(userId, taskDto.userId) &&
				Objects.equals(ownerProcessId, taskDto.ownerProcessId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, body, status, priority, creationDate, updateDate, closeDate, comments, userId, ownerProcessId);
	}

	@Override
	public String toString() {
		return "TaskDto{" +
				"title='" + title + '\'' +
				", body='" + body + '\'' +
				", status='" + status + '\'' +
				", priority=" + priority +
				", creationDate=" + creationDate +
				", updateDate=" + updateDate +
				", closeDate=" + closeDate +
				", comments=" + comments +
				", userId=" + userId +
				", ownerProcessId=" + ownerProcessId +
				'}';
	}
}
