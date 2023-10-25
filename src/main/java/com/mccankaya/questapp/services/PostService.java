package com.mccankaya.questapp.services;

import com.mccankaya.questapp.entities.Post;
import com.mccankaya.questapp.entities.User;
import com.mccankaya.questapp.repos.PostRepository;
import com.mccankaya.questapp.requests.PostCreateRequest;
import com.mccankaya.questapp.requests.PostUpdateRequest;
import com.mccankaya.questapp.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post createPost() {
        return null;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> postList;
        if (userId.isPresent()) {
            postList = postRepository.findByUserId(userId.get());
        } else {
            postList = postRepository.findAll();
        }
        return postList.stream().map(PostResponse::new).collect(Collectors.toList());

    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if (user == null) return null;

        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setUser(user);
        toSave.setTitle(newPostRequest.getTitle());
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;

        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
