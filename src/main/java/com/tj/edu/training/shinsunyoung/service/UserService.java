package com.tj.edu.training.shinsunyoung.service;

import com.tj.edu.training.shinsunyoung.config.jwt.JwtProperties;
import com.tj.edu.training.shinsunyoung.config.jwt.TokenProvider;
import com.tj.edu.training.shinsunyoung.model.User;
import com.tj.edu.training.shinsunyoung.model.dto.AddUserRequest;
import com.tj.edu.training.shinsunyoung.model.dto.LoginRequest;
import com.tj.edu.training.shinsunyoung.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;

    public Long register(AddUserRequest dto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return userRepository.save(
                User.builder()
                        .email(dto.getEmail())
                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                        .build()).getId();
    }

    // 로그인 검증
    public User login(LoginRequest loginrequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        TokenProvider tokenProvider = new TokenProvider(jwtProperties);
        // 이메일이 일치하는 모든 사용자 정보를 가져온다.
        Optional<User> optUser = userRepository.findByEmail(loginrequest.getEmail());
        User user = optUser.get();
        // 이메일이 일치하는 사용자 존재 여부 체크
//        if(!Objects.isNull(user)) {
//            // 입력한 비밀번호와 가져온 사용자 비밀번호 일치 여부 체크
//            String reqPwd = bCryptPasswordEncoder.encode(loginrequest.getPassword());
//            if(reqPwd.equals(user.getPassword())) {
//                // 액세스 토큰 발급
//                String accessToken = tokenProvider.generateToken(user, Duration.ofHours(2));
//                // 액세스 토큰을 사용자 DB 정보로 저장
//                user.setAccessToken(accessToken);
//                // 액세스 토큰을 자바 사용자 객체에 저장
//                userRepository.save(user);
//                // 해당 사용자에 대한 ROLE을 저장 (optional)
//            }
//
//        } else {
//            throw new IllegalArgumentException("Unexpected User DATA");
//        }
        return user;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user ID"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user email"));
    }
}
