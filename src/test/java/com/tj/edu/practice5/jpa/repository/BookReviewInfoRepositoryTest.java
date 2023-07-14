package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Book;
import com.tj.edu.practice5.jpa.model.BookReviewInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    private Book givenBook() {
        Book book = Book.builder()
                .name("아주 쉬운 스프링 부트 3.1")
//                .author("저자 미상")
                .build();

        return bookRepository.save(book);
    }

    private BookReviewInfo givenBookReviewInfo(Book book) {
        BookReviewInfo bookReviewInfo = BookReviewInfo.builder()
                .book(book)
                .avgReviewScore(3.0f)
                .reviewCount(0)
                .build();

        return bookReviewInfoRepository.save(bookReviewInfo);
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void crudTest1() {
//        BookReviewInfo bookReviewInfo = BookReviewInfo.builder()
//                .bookId(1L)
//                .reviewCount(0)
//                .avgReviewScore(2.3f)
//                .build();
//
//        bookReviewInfoRepository.save(bookReviewInfo);
//        System.out.println(">>> " + bookReviewInfo);
    }

    @Test
    void crudTest2() {
//        Book book = Book.builder()
//                .name("아주 쉬운 스프링 부트 3.1")
//                .author("저자 미상")
//                .build();
//
//        bookRepository.save(book);
//        System.out.println(">>> " + book);
//
//        BookReviewInfo bookReviewInfo = BookReviewInfo.builder()
//                .bookId(1L)
//                .reviewCount(0)
//                .avgReviewScore(2.3f)
//                .build();
//
//        bookReviewInfoRepository.save(bookReviewInfo);
//
//        List<BookReviewInfo> bookReviewInfoList = bookReviewInfoRepository.findAll();
//        bookReviewInfoList.forEach(System.out::println);
    }

    @Test
    void oneToOneTest1() {
        Book book = givenBook();
        BookReviewInfo bookReviewInfo = givenBookReviewInfo(book);
//        List<BookReviewInfo> bookReviewInfoList = bookReviewInfoRepository.findAll();
//        bookReviewInfoList.forEach(System.out::println);
        System.out.println(bookReviewInfoRepository.findAll());    // join하지 않고 select 한다.
        System.out.println(bookReviewInfoRepository.findById(1L)); // OneToOne optional에 따라 outer / inner join한다.
    }

    @Test
    void oneToOneTest2() {
        Book book = givenBook();
        BookReviewInfo bookReviewInfo = givenBookReviewInfo(book);
//        List<BookReviewInfo> bookReviewInfoList = bookReviewInfoReopository.findAll();
//        bookReviewInfoList.forEach(System.out::println);
        System.out.println(bookRepository.findAll());    // join하지 않고 select 한다.
        System.out.println(bookRepository.findById(1L)); // OneToOne optional에 따라 outer / inner join한다.
    }
}
