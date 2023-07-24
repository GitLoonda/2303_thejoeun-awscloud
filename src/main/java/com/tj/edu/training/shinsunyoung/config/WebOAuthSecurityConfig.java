package com.tj.edu.training.shinsunyoung.config;

import com.tj.edu.training.shinsunyoung.config.jwt.TokenProvider;
import com.tj.edu.training.shinsunyoung.config.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.tj.edu.training.shinsunyoung.config.oauth.OAuth2SuccessHandler;
import com.tj.edu.training.shinsunyoung.config.oauth.OAuth2UserCustomService;
import com.tj.edu.training.shinsunyoung.model.User;
import com.tj.edu.training.shinsunyoung.repository.RefreshTokenRepository;
import com.tj.edu.training.shinsunyoung.service.UserService;
import com.tj.edu.training.shinsunyoung.utils.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class WebOAuthSecurityConfig {

    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
                .requestMatchers("/img/**", "/css/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 토큰 방식으로 인증을 위해서 폼로그인과 세션방식 비활성화
        http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
//                .logout(AbstractHttpConfigurer::disable)
        ;
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 헤더 확인을 위해 커스텀 필터 추가
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 토큰 발급 혹은 재발급 URL은 인증 없이 접근이 가능하도록 설정
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/api/token").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll());

        http.oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/oauth2login")
                // Authorization요청과 관련된 상태 저장
                .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                        .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository()))
                // 인증 성공시 실행할 핸들러
//                .defaultSuccessUrl("/articles", true)
//                .successHandler(oAuth2SuccessHandler())
                .successHandler(successHandler())
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                        .userService(oAuth2UserCustomService)));

        // 로그아웃 성공시 이동할 URL
        http.logout(logout -> logout
                .logoutSuccessUrl("/oauth2login"));

        // /api로 시작하는 url인 경우 401상태 코드 처리(인증이 없다라고 안내)
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        new AntPathRequestMatcher("/api/**")));

        return http.build();
    }

    private void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) Duration.ofDays(14).toSeconds();

        CookieUtil.deleteCookie(request, response, "refresh_token");
        CookieUtil.addCookie(response, "refresh_token", refreshToken, cookieMaxAge);
    }

    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString("/articles")
                .queryParam("token", token)
                .build()
                .toUriString();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String test1 = "";

            // 가져온 principal의 email(username)을 통해서 유저에 대한 모든 정보를 db에서 가져온다
            User user = userService.findByEmail((String) oAuth2User.getAttributes().get("email"));

            // 가져온 user정보를 통해서 refresh토큰을 생성
//            String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(14));

            // 가져온 user정보를 통해서 access토큰을 생성
            String accessToken = tokenProvider.generateToken(user, Duration.ofDays(1));
            // access토큰값과 이동할 targetUrl를 세팅
            String targetUrl = getTargetUrl(accessToken);

            RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
            redirectStrategy.sendRedirect(request, response, targetUrl);

//            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
//            String id = defaultOAuth2User.getAttributes().get("id").toString();
//            String body = """
//                    {"id":"%s"}
//                    """.formatted(id);

//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//            PrintWriter writer = response.getWriter();
//            writer.println(body);
//            writer.flush();
        });
    }

//    @Bean
//    public OAuth2SuccessHandler oAuth2SuccessHandler() {
////        String test1 = "";
////        return ((request, response, authentication) -> {
////
////        })
//        return new OAuth2SuccessHandler(tokenProvider,
//                refreshTokenRepository,
//                oAuth2AuthorizationRequestBasedOnCookieRepository(),
//                userService
//        );
//    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
