package com.finartz.hrtaskapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finartz.hrtaskapp.Entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
