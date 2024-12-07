package com.mcr.user.service.controller;

import com.mcr.user.service.dtos.UserDto;
import com.mcr.user.service.entities.User;
import com.mcr.user.service.payload.ApiResponse;
import com.mcr.user.service.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserServices userService;

//    public UserController(UserServices userService){
//        this.userService = userService;
//    }
//

    public UserController(UserServices userService) {
        this.userService = userService;
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDto1 = userService.saveUser(userDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(userDto1);

    }


    //update
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody  UserDto userDto, @PathVariable  String userId){
        UserDto userDto1 = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(userDto1);

    }

    //getAll
    @GetMapping("/allUser")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> allUser = userService.getAllUser();
         return ResponseEntity.ok(allUser);
    }

    //getOne
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable String  userId){
        UserDto singleUser = userService.getSingleUser(userId);
        return  ResponseEntity.ok(singleUser);
    }

    //delete
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        ApiResponse appBuild = ApiResponse.builder()
                .message("User deleted Successfully !!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(appBuild);

    }
}
