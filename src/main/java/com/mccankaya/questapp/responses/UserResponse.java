package com.mccankaya.questapp.responses;

import com.mccankaya.questapp.entities.User;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private int avatarId;
    private String userName;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.avatarId = entity.getAvatarId();
        this.userName = entity.getUserName();
    }
}
