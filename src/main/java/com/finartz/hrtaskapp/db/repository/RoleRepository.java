package com.finartz.hrtaskapp.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finartz.hrtaskapp.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
