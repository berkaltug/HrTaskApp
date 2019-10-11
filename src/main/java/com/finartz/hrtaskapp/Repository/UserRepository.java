package com.finartz.hrtaskapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.finartz.hrtaskapp.Entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer>{
	
	User findByName(String name);
	User findByUsername(String username);
	
}
