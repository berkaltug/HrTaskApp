package com.finartz.hrtaskapp.Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	
	@NotEmpty
	private String surname;
	@JsonIgnoreProperties("user")
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	private List<Task> tasks=new ArrayList<Task>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_role",
	joinColumns= {@JoinColumn(name="user_id")},
	inverseJoinColumns= {@JoinColumn(name="role_id")})
	private List<Role> roles=new ArrayList<Role>();
	
	public User() {
	}

	public User(@NotEmpty String name, @NotEmpty String surname, List<Task> tasks, List<Role> roles) {
		this.name = name;
		this.surname = surname;
		this.tasks = tasks;
		this.roles = roles;
	}

	public User(Integer id, @NotEmpty String name, @NotEmpty String surname, List<Task> tasks, List<Role> roles) {
		this.userId = id;
		this.name = name;
		this.surname = surname;
		this.tasks = tasks;
		this.roles = roles;
	}

	public Integer getId() {
		return userId;
	}

	public void setId(Integer id) {
		this.userId = id;
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
		List<Task> pagedTasks =
				tasks
				.stream()
				.filter( task -> tasks.indexOf(task) >= pageMin && tasks.indexOf(task) <= pageMax )
				.collect(Collectors.toList());
		
		return pagedTasks;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", name=" + name + ", surname=" + surname + ", tasks=" + tasks + ", roles=" + roles
				+ "]";
	}



	
}
