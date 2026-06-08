package com.notzed.linkedin_App.repository;

import com.notzed.linkedin_App.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);


    void deletePostLikeByUserIdAndPostId(Long userId, Long postId);
}
