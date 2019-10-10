package com.finartz.hrtaskapp.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.finartz.hrtaskapp.Entity.User;
import com.finartz.hrtaskapp.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository	userRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Integer id) {

		try {
			return userRepository.getOne(id);
		}catch(Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public User getUserByName(String name) {
		//düzenlencek
		return null;
	}

	@Override
	public User addUser(User user) {
		try {
			return userRepository.save(user);
		}catch(Exception e) {
			System.err.println(e);
			return null;
		}
	}

}
