package com.finartz.hrtaskapp.model.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="user_table")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	@NotEmpty
	private String name;

	@Column(unique = true)
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	@Email
	private String email;
	
	@NotEmpty
	private String surname;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	private List<Task> tasks=new ArrayList<>();
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns= {@JoinColumn(name="user_id")},
	inverseJoinColumns= {@JoinColumn(name="role_id")})
	private List<Role> roles=new ArrayList<>();
	
	public User() {
	}

	public User(@NotEmpty String name, @NotNull String username, @NotNull String password, @NotNull @Email String email,
			@NotEmpty String surname, List<Task> tasks, List<Role> roles) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.surname = surname;
		this.tasks = tasks;
		this.roles = roles;
	}
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Task> getTasks(Pageable pageable) {
		//calculate pagination limits
		int pageMin = pageable.getPageNumber()*pageable.getPageSize()+1;
		int pageMax = pageable.getPageNumber()*pageable.getPageSize()+ pageable.getPageSize();
		//ordering by priority 
		tasks.sort(Comparator.comparing(Task::getPriority).reversed());
		//filtering related tasks
		return	tasks
				.stream()
				.filter( task -> tasks.indexOf(task)+1 >= pageMin && tasks.indexOf(task)+1 <= pageMax )
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", name=" + name + ", surname=" + surname + ", tasks=" + tasks + ", roles=" + roles
				+ "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return userId.equals(user.userId) &&
				name.equals(user.name) &&
				username.equals(user.username) &&
				password.equals(user.password) &&
				email.equals(user.email) &&
				surname.equals(user.surname) &&
				tasks.equals(user.tasks) &&
				roles.equals(user.roles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, name, username, password, email, surname, tasks, roles);
	}
}
