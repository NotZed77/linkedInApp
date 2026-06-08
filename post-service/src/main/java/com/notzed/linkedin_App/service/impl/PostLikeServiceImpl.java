package com.notzed.linkedin_App.service.impl;

import com.notzed.linkedin_App.entity.Post;
import com.notzed.linkedin_App.entity.PostLike;
import com.notzed.linkedin_App.exception.BadRequestException;
import com.notzed.linkedin_App.exception.ResourceNotFoundException;
import com.notzed.linkedin_App.repository.PostLikeRepository;
import com.notzed.linkedin_App.repository.PostRepository;
import com.notzed.linkedin_App.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public void likePost(Long userId, Long postId) {
        log.info("Attempting to like the post with ID: {}", postId);
        boolean exists = postRepository.existsById(postId);
        if (!exists) {
            throw new ResourceNotFoundException("Post not found with ID: " + postId);
        }

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (alreadyLiked) throw new BadRequestException("Cannot like the same post again.");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);
        log.info("Post with ID: {} liked successfully.", postId);

    }

    @Override
    @Transactional
    public void unLikePost(Long postId, Long userId) {
        log.info("Attempting to unlike the post with ID: {}", postId);
        boolean exists = postRepository.existsById(postId);
        if (!exists) {
            throw new ResourceNotFoundException("Post not found with ID: " + postId);
        }

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (!alreadyLiked) throw new BadRequestException("Cannot unlike the post which is not liked.");

        postLikeRepository.deletePostLikeByUserIdAndPostId(userId, postId);

        log.info("Post with ID: {} unliked successfully.", postId);

    }
}

