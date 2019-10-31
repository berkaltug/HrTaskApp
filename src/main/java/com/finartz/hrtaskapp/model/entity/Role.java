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
	private String roleName;
	
	public Role() {
	}

	public Role(Integer id, @NotEmpty String roleName) {
		this.roleId = id;
		this.roleName = roleName;
	}

	public Integer getId() {
		return roleId;
	}

	public void setId(Integer id) {
		this.roleId = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [id=" + roleId + ", role=" + roleName + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role)) return false;
		Role role1 = (Role) o;
		return Objects.equals(roleId, role1.roleId) &&
				Objects.equals(roleName, role1.roleName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId, roleName);
	}
}
