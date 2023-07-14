package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Book;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest() {
        Book book = Book.builder()
                .name("아주 쉬운 스프링 부트 3.1")
                .author("저자 미상")
                .build();

        bookRepository.save(book);
        System.out.println(">>> " + book);
    }
}
