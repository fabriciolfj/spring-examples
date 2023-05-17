package com.github.fabriciolfj.javaexamples.repository;

import com.github.fabriciolfj.javaexamples.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
