package com.finartz.hrtaskapp.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role)) return false;
		Role role1 = (Role) o;
		return Objects.equals(roleId, role1.roleId) &&
				Objects.equals(role, role1.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId, role);
	}
}
