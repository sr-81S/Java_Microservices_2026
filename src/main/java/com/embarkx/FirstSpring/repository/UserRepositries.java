package com.embarkx.FirstSpring.repository;

import com.embarkx.FirstSpring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositries extends JpaRepository<User, Long> {
}
