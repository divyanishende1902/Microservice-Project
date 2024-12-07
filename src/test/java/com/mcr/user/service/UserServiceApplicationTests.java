package com.mcr.user.service;

import com.mcr.user.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserRepository userRepository;


	@Test
	void contextLoads() {
	}


}
