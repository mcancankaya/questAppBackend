package com.mccankaya.questapp.repos;

import com.mccankaya.questapp.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByUserId(Long userId);
}
