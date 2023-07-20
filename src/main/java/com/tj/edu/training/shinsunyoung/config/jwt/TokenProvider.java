package com.tj.edu.training.shinsunyoung.config.jwt;

import com.tj.edu.training.shinsunyoung.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TokenProvider {
    private final JwtProperties jwtProperties;

    // 외부 클래스 jwt 토큰 생성
    public String generateToken(User user, Duration expireAt) {
        Date now = new Date();

        return buildToken(new Date(now.getTime() + expireAt.toMillis()), user);
    }

    // jwt 토큰 생성
    private String buildToken(
            Date expireDate
            , User user
    ) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)  // 헤더 type : JWT
                .setIssuer(jwtProperties.getIssuer())   // application.yml에 있는 jwt1@abc.om
                .setIssuedAt(now)   // token 발행 시간
                .setExpiration(expireDate)  // token 만료 시간
                .setSubject(user.getEmail())    // token 제목
                .claim("id", user.getId())  // 클레임id
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // jwt 토큰 유효성 검증
    public boolean validJwtToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // decoding
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // jwt 토큰 기반 인증 정보 가져오기
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities
                = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(
                        claims.getSubject(),
                        "",
                        authorities),
                token, authorities);
    }

    // jwt 클레임을 조회
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
