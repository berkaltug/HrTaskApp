package com.finartz.hrtaskapp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer roleId;
	@NotEmpty
	private String role;
	
	public Role() {
	}

	public Role(Integer id, @NotEmpty String role) {
		this.roleId = id;
		this.role = role;
	}

	public Integer getId() {
		return roleId;
	}

	public void setId(Integer id) {
		this.roleId = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + roleId + ", role=" + role + "]";
	}
	
	
	
	
}
