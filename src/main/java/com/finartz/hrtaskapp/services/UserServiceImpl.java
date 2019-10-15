package com.finartz.hrtaskapp.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finartz.hrtaskapp.db.repository.RoleRepository;
import com.finartz.hrtaskapp.db.repository.UserRepository;
import com.finartz.hrtaskapp.model.entity.User;

@Service
public class UserServiceImpl implements UserService{
	public static final int ROLE_USER=2;
	private UserRepository	userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RoleRepository roleRepository;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}

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
	public List<User> getUserByName(String name) {
		try {
			
			return userRepository.findLikeName(name);
		
		}catch(Exception e) {
			
			System.err.println(e.getMessage());
			return null;
		
		}
	}
	
	@Override
	public void addUser(User user) {
		//adding user role by default
		user.getRoles().add(roleRepository.findById(ROLE_USER).get());
		//check to ensure if password in correct format or not
		try {
			if(!user.getPassword().contains("$2a$10$")) {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			}
			userRepository.save(user);
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	public String findLoggedInUsername() {
        
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (principal!=null) {
            UserDetails ud=(UserDetails)principal;
            return ud.getUsername();
        }

        return null;
    }
}
