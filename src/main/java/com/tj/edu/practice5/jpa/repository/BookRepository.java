package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Book;
import com.tj.edu.practice5.jpa.model.BookAndId;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select b from Book b where name = ?1")
    List<Book> findByMyBooks(String name);

    @Query(value = "select b from Book b where name = ?1 and id = ?2")
    List<Book> findByMyBooksAndMyId(String name, Long id);

    @Query(value = "select b from Book b where id = ?1 and name = ?2")
    List<Book> findByMyIdAndMyBooks(Long id, String name);

    @Query(value = "select b.name from Book b where name = ?1")
    List<String> findNameByMyBooks(String name);

    // return 해주는 book 객체를 convert 가 안되서 안되는 메소드
//    @Query(value = "select b.id id, b.name name from Book b where name = ?1")
//    List<Book> findNameIdByMyBooks(String name);

    // 리턴타입을 모든 객체로 convert 할 수 있도록 Map<String, Object>의 형태로 받는 메소드
    @Query(value = "select b.id id, b.name name from Book b where name = ?1")
    List<Map<String, Object>> findNameIdByMyBooks(String name);

    @Query(value = "select b.id id, b.name name from Book b where name like %:name%")
    List<Map<String, Object>> findByNamedNameIdByMyBooks(@Param("name") String name);

    // 커스텀 인터페이스를 사용하여 getter의 형태로만 구현된 JPQL
    @Query(value = "select b.id identifier, b.name letter from Book b where name like %:name%")
    List<BookAndId> findByCustomByMyBooks(@Param("name") String name);

    // Tuple 타입으로 리턴받는 JPQL
    @Query(value = "select b.id identifier, b.name letter from Book b where name like %:name%")
    List<Tuple> findByTupleByMyBooks(@Param("name") String name);

    // 네이티브 SQL 문법 사용이 가능한 JPQL
    @Query(value = "select * from book", nativeQuery = true)
    List<Book> findByNativeByMyBooks();

    @Query(value = "select * from book where name like %:name%", nativeQuery = true)
    List<Book> findByNativeByMyBooks(@Param("name") String name);

    @Query(value = "select * from book where id = :id and name = :name", nativeQuery = true)
    List<Book> findByNativeByMyBooks(@Param("id") Long id, @Param("name") String name);

    // 네이티브 QUERY로 UPDATE 문 작성하기
    @Query(value = "update book set name = :name where id = :id", nativeQuery = true)
    @Modifying
    int updateSpecificName(@Param("id") Long id, @Param("name") String name);

    // 네이티브 QUERY로 INSERT 문 작성하기
    @Query(value = "insert into book(`name`) values (:name)", nativeQuery = true)
    @Modifying
    void insertBook(@Param("name") String name);

    // 네이티브 QUERY로 DELETE 문 작성하기
    @Query(value = "delete from book where name = :name", nativeQuery = true)
    @Modifying
    void deleteBookByName(@Param("name") String name);

}
