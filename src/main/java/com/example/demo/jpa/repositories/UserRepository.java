package com.example.demo.jpa.repositories;

import com.example.demo.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
