package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.*;
import com.tj.edu.practice5.jpa.model.enums.Nation;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void bookTest() {
        Book book = givenBook(givenAuthor(), givenPublisher());
        bookRepository.findAll();
    }

    @Test
    void bookRelationTest() {
        giveBookAndReview();

        Member member = memberRepository.findByEmail("mars@thejoeun.com");
        System.out.println("Member >>> " + member);
        System.out.println("Review >>> " + member.getReviews());
        System.out.println("Book >>> " + member.getReviews().get(0).getBook());
        System.out.println("Publisher >>> " + member.getReviews().get(0).getBook().getPublisher());
//        System.out.println("Author >>> " + member.getReviews().get(0).getBook().getAuthor());
    }

    @Test
    @Transactional
    void bookRelationTest2() {
        giveBookAndReview();
        Member member = memberRepository.findByEmail("mars@thejoeun.com");
        Book book = member.getReviews().get(0).getBook();
        Publisher publisher = book.getPublisher();
//        Author author = book.getAuthor();
    }

    private Review giveBookAndReview() {
        return givenReview(givenMember(), givenBook(givenAuthor(), givenPublisher()));
    }


    private Book givenBook(Author author, Publisher publisher) {
        Book book = Book.builder()
                .name("아주 쉬운 스프링 부트 3.1")
//                .author(author)
                .publisher(publisher)
                .build();

        return bookRepository.save(book);
    }

    private Review givenReview(Member member, Book book) {
        Review review = Review.builder()
                .title("독서후기")
                .content("너무재밌었다")
                .member(member)
                .book(book)
                .build();

        return reviewRepository.save(review);
    }

    private Member givenMember() {
        return memberRepository.findByEmail("mars@thejoeun.com");
    }

    private Publisher givenPublisher() {
        Publisher publisher = Publisher.builder()
                .name("한빛아카데미")
                .build();

        return publisherRepository.save(publisher);
    }

    private Author givenAuthor() {
        Author author = Author.builder()
                .name("김길환")
                .build();

        return authorRepository.save(author);
    }
}
