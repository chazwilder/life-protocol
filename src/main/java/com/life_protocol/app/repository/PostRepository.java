package com.life_protocol.app.repository;

import com.life_protocol.app.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByUserId(String userId);
    List<Post> findByUserIdIn(List<String> userIds);
    List<Post> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<Post> findByContentContainingIgnoreCase(String keyword);
    List<Post> findTop10ByOrderByTimestampDesc();
}