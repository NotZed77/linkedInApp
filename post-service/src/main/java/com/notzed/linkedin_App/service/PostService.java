package com.notzed.linkedin_App.service;

import com.notzed.linkedin_App.dto.PostCreateRequestDto;
import com.notzed.linkedin_App.dto.PostDto;
import com.notzed.linkedin_App.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostsOfUser(Long userId);
}
