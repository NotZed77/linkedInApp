package com.notzed.linkedin_App.service;

public interface PostLikeService {
    public void likePost(Long userId, Long postId);

    void unLikePost(Long postId, Long userId);
}
