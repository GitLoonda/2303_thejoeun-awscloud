package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Test
    void transactionalTest() {
        usersService.put();
    }
}
