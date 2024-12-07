package com.mcr.user.service.repository;

import com.mcr.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,String> {
}
