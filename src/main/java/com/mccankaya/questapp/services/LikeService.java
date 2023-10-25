package com.mccankaya.questapp.services;

import com.mccankaya.questapp.entities.Like;
import com.mccankaya.questapp.entities.Post;
import com.mccankaya.questapp.entities.User;
import com.mccankaya.questapp.repos.LikeRepository;
import com.mccankaya.questapp.requests.LikeCreateRequest;
import com.mccankaya.questapp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Like createOneLike(LikeCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        Like like = new Like();
        if (user!=null && post!=null){
            like.setUser(user);
            like.setPost(post);
            return likeRepository.save(like);
        }
        return null;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId){
        List<Like> list;
        if (userId.isPresent() && postId.isPresent())  {
            list =  likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list =  likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else{
            list = likeRepository.findAll();
        }
        return list.stream().map(LikeResponse::new).collect(Collectors.toList());
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }


}
