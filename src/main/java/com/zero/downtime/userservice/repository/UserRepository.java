package com.zero.downtime.userservice.repository;

import com.zero.downtime.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
