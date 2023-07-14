package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
//    Set<Member> findByEmail(String email);
    List<Member> findByName(String name);
    Page<Member> findByName(String name, Pageable pageable);
//    Optional<Member> findByCreateAt(LocalDateTime localDateTime);
    boolean existsByEmail(String email);
    int countByName(String name);
    List<Member> findFirst1ByEmail(String email);
    List<Member> findTop1ByEmail(String email);
    List<Member> findDistinctByEmail(String email);

    List<Member> findByNameAndEmail(String name, String email);
    List<Member> findByNameOrEmail(String name, String email);
    List<Member> findByIdAndNameAndEmail(Long id, String name, String email);
    List<Member> findByIdOrNameOrEmail(Long id, String name, String email);
    List<Member> findByIdAndNameOrEmail(Long id, String name, String email);
    List<Member> findByIdOrNameAndEmail(Long id, String name, String email);

//    List<Member> findByCreateAtAfter(LocalDateTime createAt);
//    List<Member> findByCreateAtBefore(LocalDateTime createAt);
    List<Member> findByIdGreaterThan(Long id);
//    List<Member> findByCreateAtLessThan(LocalDateTime createAt);
    List<Member> findByIdGreaterThanEqualOrIdLessThanEqual(Long id1, Long id2);
    List<Member> findByNameLike(String likeName);
    List<Member> findByEmailLike(String likeEmail);
    List<Member> findByNameIn(List<String> nameList);
    List<Member> findByNameContains(String fragment);

    List<Member> findByNameOrderByIdDesc(String name);
//    List<Member> findByAddressIsEmpty();
//    List<Member> findByAddressIsNotEmpty();
    List<Member> findByMaleIsFalse();
}

