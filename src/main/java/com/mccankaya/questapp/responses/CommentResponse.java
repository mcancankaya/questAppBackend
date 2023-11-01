package com.mccankaya.questapp.responses;

import com.mccankaya.questapp.entities.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private Long userId;
    private String userName;
    private String text;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.userName = comment.getUser().getUserName();
        this.text = comment.getText();
    }
}
