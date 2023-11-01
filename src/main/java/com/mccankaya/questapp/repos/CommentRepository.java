package com.mccankaya.questapp.repos;

import com.mccankaya.questapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);
    @Query(nativeQuery = true, value = "select 'commented on', c.post_id, u.avatar_id, u.user_name from comments c left join users u on u.id = c.user_id where c.post_id in :postIds limit 5")
    List<Object> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);
}
