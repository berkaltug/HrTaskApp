package com.finartz.hrtaskapp.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.finartz.hrtaskapp.db.repository.RoleRepository;
import com.finartz.hrtaskapp.db.repository.UserRepository;
import com.finartz.hrtaskapp.model.dto.UserDto;
import com.finartz.hrtaskapp.model.entity.User;
import com.finartz.hrtaskapp.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
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
	public Page<UserDto> getAllUsers(Integer pageNo) {
		Pageable pageable=PageRequest.of(pageNo, 10, Sort.by("name"));
		return userRepository
				.findAll(pageable)
				.map(user-> modelMapper.map(user, UserDto.class));
	}

	@Override
	public Optional<User> getUser(Integer id){
			return userRepository.findById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}


	@Override
	public List<User> getUserByName(String name) {
		try {
			return userRepository.findLikeName(name);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return Collections.emptyList();
		}
	}
	
	@Override
	public User addUser(User user) {
		//adding user role by default
		user.getRoles().add(roleRepository.findById(ROLE_USER).get());
		//check to ensure if password in correct format or not
			if(!user.getPassword().contains("$2a$10$")) {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			}
			return userRepository.save(user);
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

	@Override
	public Collection<GrantedAuthority> findLoggedInRoles() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal!=null){
			UserDetails ud=(UserDetails)principal;
			return (Collection<GrantedAuthority>)ud.getAuthorities();
		}else{
			return Collections.emptyList();
		}
	}

	@Override
	public boolean isAdmin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails ud=(UserDetails)principal;
		return ud.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
}
