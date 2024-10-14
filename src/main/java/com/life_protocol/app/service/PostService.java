package com.life_protocol.app.service;

import com.life_protocol.app.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);
    Optional<Post> getPostById(String id);
    List<Post> getPostsByUserId(String userId);
    List<Post> getPostsByUserIds(List<String> userIds);
    List<Post> getPostsByDateRange(LocalDateTime start, LocalDateTime end);
    List<Post> searchPosts(String keyword);
    List<Post> getRecentPosts(int limit);
    Post updatePost(Post post);
    void deletePost(String id);
}