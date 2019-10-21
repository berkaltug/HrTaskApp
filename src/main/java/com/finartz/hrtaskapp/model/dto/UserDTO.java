package com.finartz.hrtaskapp.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
	
	private Integer userId;
	private String name;
	private String surname;
	@JsonIgnore
	private String password;
	private String username;
	private String email;
	private List<TaskDTO> tasks=new ArrayList<TaskDTO>();
	
	public UserDTO() {
	}

	public UserDTO(Integer userId, String name, String surname, String password, String username, String email,
			List<TaskDTO> tasks) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.username = username;
		this.email = email;
		this.tasks = tasks;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<TaskDTO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserDTO)) return false;
		UserDTO userDTO = (UserDTO) o;
		return Objects.equals(userId, userDTO.userId) &&
				Objects.equals(name, userDTO.name) &&
				Objects.equals(surname, userDTO.surname) &&
				Objects.equals(password, userDTO.password) &&
				Objects.equals(username, userDTO.username) &&
				Objects.equals(email, userDTO.email) &&
				Objects.equals(tasks, userDTO.tasks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, name, surname, password, username, email, tasks);
	}
}
