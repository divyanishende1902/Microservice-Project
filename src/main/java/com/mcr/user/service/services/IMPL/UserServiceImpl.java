package com.mcr.user.service.services.IMPL;

import com.mcr.user.service.dtos.UserDto;
import com.mcr.user.service.entities.Hotel;
import com.mcr.user.service.entities.User;
import com.mcr.user.service.exception.ResourceNotFoundException;
import com.mcr.user.service.entities.Rating;
import com.mcr.user.service.external.services.HotelService;
import com.mcr.user.service.repository.UserRepository;
import com.mcr.user.service.services.UserServices;
import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Override
    public UserDto saveUser(UserDto userDto) {
        String id = UUID.randomUUID().toString();
        userDto.setUserId(id);
        User user = mapToEntity( userDto);
        User savedUser = userRepository.save(user);
        UserDto  userDto1 = mapToDto(savedUser);
        return userDto1;
    }

    private UserDto mapToDto(User savedUser) {
        return mapper.map(savedUser, UserDto.class);
    }

    private User mapToEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
      try {

          User user = userRepository.findById(userId)
                  .orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));

//          user.setName(userDto.getName());
//          user.setAbout(userDto.getAbout());
//          user.setEmail(userDto.getEmail());

          // Only update non-null values from userDto
          if (userDto.getName() != null) {
              user.setName(userDto.getName());
          }
          if (userDto.getAbout() != null) {
              user.setAbout(userDto.getAbout());
          }

          if (userDto.getEmail() != null) {
              // You could add additional validation for email format here
              user.setEmail(userDto.getEmail());
          }

          User savedUser = userRepository.save(user);
          return  mapper.map(savedUser, UserDto.class);



      }catch(Exception ex){
          System.out.println(ex);
          throw new RuntimeException("Error updating user", ex);
      }

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allUser = userRepository.findAll();
        List<UserDto> collect = allUser.stream()
                .map((obj) -> mapToDto(obj))
                .collect(Collectors.toList());
         return collect;
    }

    @Override
    public UserDto getSingleUser(String userId) {
        //get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));


        //fetch rating of the above user from rating service
        //http://localhost:8083/ratings/users/45f2b3df-b301-4562-83fe-cf77660e1ad3
        Rating[] ratingsOfUser = restTemplate.getForObject("http://REVIEWSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        //calling hotel service

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/b16b2f74-bd8f-473d-8c27-491f8628e864

           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //logger.info("Response status code: {}",forEntity.getStatusCode());
            //set the hotel rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return mapToDto(user);
    }

    @Override
    public String deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));
        userRepository.delete(user);
        return "user Deleted successfully";
    }
}
