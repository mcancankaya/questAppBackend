package com.mccankaya.questapp.services;

import com.mccankaya.questapp.entities.Comment;
import com.mccankaya.questapp.entities.Post;
import com.mccankaya.questapp.entities.User;
import com.mccankaya.questapp.repos.CommentRepository;
import com.mccankaya.questapp.requests.CommentCreateRequest;
import com.mccankaya.questapp.requests.CommentUpdateRequest;
import com.mccankaya.questapp.responses.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }


    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if (userId.isPresent() && postId.isPresent()) {
            comments =  commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            comments =  commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            comments =  commentRepository.findByPostId(postId.get());
        } else
            comments =  commentRepository.findAll();
        return comments.stream().map(CommentResponse::new).collect(Collectors.toList());
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (user != null && post != null) {
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(request.getText());
            comment.setCreateDate(new Date());
            return commentRepository.save(comment);
        } else
            return null;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment toUpdate = comment.get();
            toUpdate.setText(request.getText());
            return commentRepository.save(toUpdate);
        } else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
