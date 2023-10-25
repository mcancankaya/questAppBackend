package com.mccankaya.questapp.controllers;

import com.mccankaya.questapp.entities.Like;
import com.mccankaya.questapp.requests.LikeCreateRequest;
import com.mccankaya.questapp.responses.LikeResponse;
import com.mccankaya.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest request){
        return likeService.createOneLike(request);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }
}
