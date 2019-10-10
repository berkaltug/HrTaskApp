package com.finartz.hrtaskapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finartz.hrtaskapp.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
}
