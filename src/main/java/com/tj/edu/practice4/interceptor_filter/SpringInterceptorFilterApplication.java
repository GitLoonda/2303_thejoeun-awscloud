package com.tj.edu.practice4.interceptor_filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Filter 보충설명
 *   (1) https://veneas.tistory.com/entry/Spring-Boot-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-%ED%95%84%ED%84%B0-%EC%A0%81%EC%9A%A9-Filter
 *   (2) https://jronin.tistory.com/124
 *   (3) https://jangjjolkit.tistory.com/7
 *   (4) https://taetaetae.github.io/2020/04/06/spring-boot-filter/
 */
@SpringBootApplication
@ServletComponentScan
public class SpringInterceptorFilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringInterceptorFilterApplication.class, args);
    }
}
