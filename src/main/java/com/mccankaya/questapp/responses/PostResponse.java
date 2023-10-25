package com.mccankaya.questapp.responses;

import com.mccankaya.questapp.entities.Like;
import com.mccankaya.questapp.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String text;
    private List<LikeResponse> postLikes;
    public PostResponse(Post entity, List<LikeResponse> postLikes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes = postLikes;
    }
}
