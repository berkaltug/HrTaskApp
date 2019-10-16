package com.finartz.hrtaskapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.finartz.hrtaskapp.db.repository.RoleRepository;
import com.finartz.hrtaskapp.db.repository.UserRepository;
import com.finartz.hrtaskapp.model.dto.UserDTO;
import com.finartz.hrtaskapp.model.entity.User;
import com.finartz.hrtaskapp.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	public static final int ROLE_USER=2;
	private UserRepository	userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RoleRepository roleRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			RoleRepository roleRepository,ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
		this.modelMapper=modelMapper;
	}

	@Override
	public Page<UserDTO> getAllUsers(Integer pageNo) {
		Pageable pageable=PageRequest.of(pageNo, 10, Sort.by("name"));
		Page<UserDTO> allUsersDTO=userRepository
				.findAll(pageable)
				.map(page-> modelMapper.map(page, UserDTO.class));
		return allUsersDTO;
	
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
