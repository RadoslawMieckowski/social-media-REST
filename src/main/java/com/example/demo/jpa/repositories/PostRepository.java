package com.example.demo.jpa.repositories;

import com.example.demo.beans.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
