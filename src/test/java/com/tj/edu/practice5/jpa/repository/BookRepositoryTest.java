package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.*;
import com.tj.edu.practice5.jpa.model.enums.Nation;
import jakarta.persistence.Tuple;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Test
    void jpqlTest1() {
        List<Book> bookList = bookRepository.findByMyBooks("재미있는 자바책");
        bookList.forEach(System.out::println);

        System.out.println("=========================================");

        List<Book> bookList2 = bookRepository.findByMyBooksAndMyId("재미있는 자바책", 2L);
        bookList2.forEach(System.out::println);

        System.out.println("=========================================");

        List<Book> bookList3 = bookRepository.findByMyIdAndMyBooks(2L, "재미있는 자바책");
        bookList3.forEach(System.out::println);

        System.out.println("=========================================");

        List<String> bookList4 = bookRepository.findNameByMyBooks("재미있는 자바책");
        bookList4.forEach(System.out::println);

        System.out.println("=========================================");

        // 메소드에서 return 해주는 book 객체를 convert 하지 못해 에러 발생
//        List<Book> bookList5 = bookRepository.findNameIdByMyBooks("재미있는 자바책");
//        bookList5.forEach(System.out::println);
        List<Map<String, Object>> listMap1 = bookRepository.findNameIdByMyBooks("재미있는 자바책");
        listMap1.forEach(x -> System.out.println(x.entrySet()));

        List<Map<String, Object>> listMap2 = bookRepository.findByNamedNameIdByMyBooks("재미있는 자바책");
        listMap2.forEach(x -> System.out.println(x.entrySet()));

    }

    @Test
    void nativeSqlTest() {
        List<Book> bookList = bookRepository.findByNativeByMyBooks();
        bookList.forEach(System.out::println);

        List<Book> bookList2 = bookRepository.findByNativeByMyBooks("재미있는 자바책");
        bookList2.forEach(System.out::println);

        List<Book> bookList3 = bookRepository.findByNativeByMyBooks(1L, "재미있는 자바책");
        bookList3.forEach(System.out::println);
    }

    @Test
    void customModelJpaTest() {
        List<BookAndId> listMap = bookRepository.findByCustomByMyBooks("재미있는 자바책");
        listMap.forEach(s -> System.out.println(s.getIdentifier() + " : " + s.getLetter()));
    }

    @Test
    void tupleModelJpaTest() {
        List<Tuple> tupleList = bookRepository.findByTupleByMyBooks("재미있는 자바책");
        tupleList.forEach(tuple -> System.out.println(tuple.get(0) + " : " + tuple.get(1)));
    }

    @Test
    @Transactional
//    @Commit
    void updateJpaTest1() {
        int isUpdate = bookRepository.updateSpecificName(2L, "재미없는 자바책");
        System.out.println("2번 id를 가진 book의 이름 " + (isUpdate > 0 ? "바뀜" : "바뀌지 않음"));
        System.out.println(bookRepository.findById(2L));
    }

    @Test
    @Transactional
    void insertJpaTest1() {
        bookRepository.insertBook("테스트");
        bookRepository.findByNativeByMyBooks().forEach(System.out::println);
    }

    @Test
    @Transactional
    void deleteJpaTest1() {
        bookRepository.insertBook("테스트");
        bookRepository.deleteBookByName("테스트");
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
