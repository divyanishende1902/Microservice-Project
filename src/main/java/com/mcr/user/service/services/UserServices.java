package com.mcr.user.service.services;

import com.mcr.user.service.dtos.UserDto;
import com.mcr.user.service.entities.User;

import java.util.List;

public interface UserServices {

    //create
    UserDto saveUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto, String userId);

    //get All
    List<UserDto> getAllUser();

    //get single
    UserDto getSingleUser(String userId);

    //delete
    String deleteUser(String userId);

}
