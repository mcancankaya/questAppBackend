package com.mccankaya.questapp.requests;


import lombok.Data;

@Data
public class LikeCreateRequest {
    private Long userId;
    private Long postId;

}
