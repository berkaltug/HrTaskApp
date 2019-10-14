package com.finartz.hrtaskapp.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finartz.hrtaskapp.Entity.User;
import com.finartz.hrtaskapp.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository	userRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User getUser(Integer id) {

		try {
			return userRepository.findById(id).get();
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public User getUserByName(String name) {
		try {
			return userRepository.findByName(name);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	@Override
	public User addUser(User user) {
		try {
			if(!user.getPassword().contains("$2a$10$")) {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				}
			return userRepository.save(user);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	@Override
	public String findLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println(principal);
        
        if (principal!=null) {
            UserDetails ud=(UserDetails)principal;
            return ud.getUsername();
        }

        return null;
    }
}
