package com.mcr.user.service;

import com.mcr.user.service.entities.Rating;
import com.mcr.user.service.external.services.RatingService;
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

	@Autowired
	private RatingService ratingService;


	@Test
	void createRating(){
		Rating rating = Rating.builder().rating(6).userId("4ba59471-b3e1-4bcd-8c2f-839e982aaf71").hotelId("a5f04cc7-ab40-4d7e-a126-e1006668840e").feedback("this is created using feign client").build();
		Rating savedRating = ratingService.createRating(rating);
		System.out.println("New rating created!!");
	}


}

