package com.tj.edu.training.shinsunyoung.config;


import com.tj.edu.training.shinsunyoung.config.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final String HEADER_AUTHORIZATION = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청 헤더이서 Authorization 키 값 조회
        String authValueInHeader =  request.getHeader(HEADER_AUTHORIZATION);
        String token = getAccessToken(authValueInHeader);

        if(tokenProvider.validJwtToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String headerAuthToken) {
        if (headerAuthToken != null && headerAuthToken.startsWith(TOKEN_PREFIX)) {
            return headerAuthToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
