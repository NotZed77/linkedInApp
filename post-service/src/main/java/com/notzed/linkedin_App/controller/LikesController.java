package com.notzed.linkedin_App.controller;

import com.notzed.linkedin_App.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId){
        postLikeService.likePost(1L, postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unLikePost(@PathVariable Long postId){
        postLikeService.unLikePost(postId, 1L);
        return ResponseEntity.noContent().build();
    }


}
