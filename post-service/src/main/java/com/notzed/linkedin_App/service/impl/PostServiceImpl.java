package com.notzed.linkedin_App.service.impl;

import com.notzed.linkedin_App.dto.PostCreateRequestDto;
import com.notzed.linkedin_App.dto.PostDto;
import com.notzed.linkedin_App.entity.Post;
import com.notzed.linkedin_App.exception.GlobalExceptionHandler;
import com.notzed.linkedin_App.exception.ResourceNotFoundException;
import com.notzed.linkedin_App.repository.PostRepository;
import com.notzed.linkedin_App.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);

        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        log.debug("Retrieving post with ID: {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post not found with ID: " + postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostsOfUser(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);

        return posts.stream()
                .map((element) -> modelMapper.map(element, PostDto.class))
                .collect(Collectors.toList());
    }
}
