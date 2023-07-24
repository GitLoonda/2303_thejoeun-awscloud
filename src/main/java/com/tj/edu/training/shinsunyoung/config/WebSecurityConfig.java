//package com.tj.edu.training.shinsunyoung.config;
//
//
//import com.tj.edu.training.shinsunyoung.service.UserDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@RequiredArgsConstructor
//@Configuration
////@EnableWebSecurity
//public class WebSecurityConfig {
//    private final UserDetailsService userDetailsService;
//
//    /**
//     * 웹 보안 설정 control
//     *   (1) 스프링 시큐리티 비활성화(static폴더 아래에 있는 모든 것들)
//     * @return WebSecurityCustomizer
//     */
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
////                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**")
//                .requestMatchers("/images/**")
//                .requestMatchers("/api/**")
////                .requestMatchers("/**")
//                ;
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests()
//                .requestMatchers("/login", "/signup", "/user").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin((formLogin) -> {
//                    formLogin.loginPage("/login") // 사용자 정의 login페이지 사용
//                            .defaultSuccessUrl("/articles", true);
////                    formLogin.failureUrl("/error.html"); \\192.168.0.47\fullstack-cloud-course\springboot-study\2303_thejoeun-awscloud\src\main\resources\templates\articleList.html \\192.168.0.47\fullstack-cloud-course\springboot-study\2303_thejoeun-awscloud\src\main\resources\templates\error.html \\192.168.0.47\fullstack-cloud-course\springboot-study\2303_thejoeun-awscloud\src\main\resources\templates\example.html \\192.168.0.47\fullstack-cloud-course\springboot-study\2303_thejoeun-awscloud\src\main\resources\templates\login.html \\192.168.0.47\fullstack-cloud-course\springboot-study\2303_thejoeun-awscloud\src\main\resources\templates\newArticle.html \\192.168.0.47\fullstack-cloud-course\springboot-study\2303_thejoeun-awscloud\src\main\resources\templates\signup.html
//                })
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login")
//                        .invalidateHttpSession(true)
//                )
//                .build();
//    }
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
