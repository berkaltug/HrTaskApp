package com.finartz.hrtaskapp;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import com.finartz.hrtaskapp.model.entity.Role;
import com.finartz.hrtaskapp.model.entity.Task;
import com.finartz.hrtaskapp.model.entity.User;
import com.finartz.hrtaskapp.services.UserService;

@SpringBootTest
class UserServiceTests {

	@Test
	void contextLoads() {
	}

}
