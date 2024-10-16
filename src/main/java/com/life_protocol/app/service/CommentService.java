package com.life_protocol.app.service;

import com.life_protocol.app.model.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(Comment comment);
    Optional<Comment> getCommentById(String id);
    List<Comment> getCommentsByPostId(String postId);
    List<Comment> getCommentsByUserId(String userId);
    List<Comment> getCommentsByPostIdSortedByTimestamp(String postId);
    long getCommentCountByPostId(String postId);
    Comment updateComment(Comment comment);
    void deleteComment(String id);
}