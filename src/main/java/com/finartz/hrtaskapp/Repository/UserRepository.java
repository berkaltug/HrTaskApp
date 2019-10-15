package com.finartz.hrtaskapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finartz.hrtaskapp.Entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer>{
	
	@Query("select u from User u  where u.name like %?1%") 
	List<User> findLikeName( String name);
	
	User findByUsername(String username);
	
}
