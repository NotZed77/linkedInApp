package com.notzed.linkedIn.connections_service.auth;

import com.notzed.linkedin_App.auth.UserContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Long userId = UserContextHolder.getCurrentUserId();
        if(userId != null){
            requestTemplate.header("X-User-Id", String.valueOf(userId));
        }
    }
}
