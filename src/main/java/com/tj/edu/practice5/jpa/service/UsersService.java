package com.tj.edu.practice5.jpa.service;

import com.tj.edu.practice5.jpa.model.Users;
import com.tj.edu.practice5.jpa.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional
    public void put() {
        Users users = Users.builder()
                .name("테스트")
                .build();

        usersRepository.save(users);
    }
}
