package com.life_protocol.app.service;

import com.life_protocol.app.model.Post;
import com.life_protocol.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        post.setTimestamp(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getPostsByUserId(String userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public List<Post> getPostsByUserIds(List<String> userIds) {
        return postRepository.findByUserIdIn(userIds);
    }

    @Override
    public List<Post> getPostsByDateRange(LocalDateTime start, LocalDateTime end) {
        return postRepository.findByTimestampBetween(start, end);
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return postRepository.findByContentContainingIgnoreCase(keyword);
    }

    @Override
    public List<Post> getRecentPosts(int limit) {
        return postRepository.findTop10ByOrderByTimestampDesc().subList(0, Math.min(limit, 10));
    }

    @Override
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}