package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Address;
import com.tj.edu.practice5.jpa.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RepositioryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AddressRepository addressRepository;

    @DisplayName("jpa 테스트")
    @Test
    void jpaTest1() {

    }

    @DisplayName("간단한 jpa MemberRepository 테스트")
    @Test
    void memberRepositoryTest() {
        // member 데이터 insert
    }

    @Test
    void bookRepositoryTest() throws InterruptedException {
        Book book = Book.builder()
                .name("표준ORM JPA 프로그래밍")
//                .author("김한선")
                .build();
        Book book2 = bookRepository.save(book);

        Thread.sleep(1000);

//        book2.setAuthor("박동남");
        bookRepository.save(book2);
    }

    @Test
    void addressRepositoryTest() {
        Address address = Address.builder()
                .zipcode("11-12")
                .build();
        addressRepository.save(address);
    }

    @BeforeEach
    void tearDown() {

    }
}
