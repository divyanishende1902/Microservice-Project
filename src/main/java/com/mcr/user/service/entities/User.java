package com.mcr.user.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="micro_user")
public class User {

    @Id
    @Column(name="ID")
    private String userId;

    @Column(name="EMAIL")
    private String email;

    @Column(name="NAME", length = 20)
    private String name;

    @Column(name="ABOUT")
    private String about;

    @Transient
    private List<Rating>  ratings = new ArrayList<>();

}
