package com.mcr.user.service.dtos;

import com.mcr.user.service.entities.Rating;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {


    private String userId;

    private String email;

    private String name;

    private String about;


    private List<Rating> ratings;
}
