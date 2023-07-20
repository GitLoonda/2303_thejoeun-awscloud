package com.tj.edu.training.shinsunyoung.service;

import com.tj.edu.training.shinsunyoung.config.jwt.TokenProvider;
import com.tj.edu.training.shinsunyoung.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    // 로그인 인증을 통과한 사용자에게 액세스 토큰 발급
    public String createNewAccessToken(User user) {
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }

    // 리프레시 토큰으로 액세스 토큰을 발급
    public String createNewAccessToken(String refreshToken) {
        if(!tokenProvider.validJwtToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
