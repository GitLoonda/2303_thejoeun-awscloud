package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class JpaQueryMethodTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void jpaQueryMethodTest1() {
        // SELECT문 email 값을 갖는 튜플 셋을 가져오기
//        Set<Member> memberSet = memberRepository.findByEmail("leesunsin@gmail.com");
//        System.out.println(memberSet);

        // SELECT문 name 값을 갖는 튜플 리스트를 가져오기
        List<Member> memberList = memberRepository.findByName("홍길동");
        memberList.forEach(System.out::println);

        // SELECT문 createAt 값을 갖는 튜플 옵션을 가져오기
//        Optional<Member> memberOptional = memberRepository.findByCreateAt(LocalDateTime.MIN);
//        System.out.println(memberOptional);

        boolean memberExist = memberRepository.existsByEmail("kangkamchan@thejoeun.com");
        System.out.println(memberExist);

        int memberCount = memberRepository.countByName("홍길동");
        System.out.println(memberCount);

        memberRepository.findFirst1ByEmail("namsun@thejoeun.com").forEach(System.out::println);
        memberRepository.findTop1ByEmail("namsun@thejoeun.com").forEach(System.out::println);
        memberRepository.findDistinctByEmail("ryukwansun1@thejoeun.com").forEach(System.out::println);
    }

    @Test
    void jpaQueryMethodTest2() {
        memberRepository.findByNameAndEmail("홍길동", "mars@thejoeun.com")
                        .forEach(System.out::println);
        memberRepository.findByNameOrEmail("홍길동", "mars@thejoeun.com")
                        .forEach(System.out::println);
        memberRepository.findByIdAndNameAndEmail(1L, "홍길동", "mars@thejoeun.com")
                        .forEach(s -> System.out.println(s));
        memberRepository.findByIdOrNameOrEmail(2L, "홍길동", "ryukwansun1@thejoeun.com")
                        .forEach(s -> System.out.println(s));
        memberRepository.findByIdAndNameOrEmail(5L, "홍길동", "namsun@thejoeun.com")
                        .forEach(s -> System.out.println(s));
        memberRepository.findByIdOrNameAndEmail(1L, "홍길동", "namsun@thejoeun.com")
                        .forEach(s -> System.out.println(s));

//        memberRepository.findByCreateAtAfter(LocalDateTime.now()).forEach(s -> System.out.println(s));
//        memberRepository.findByCreateAtBefore(LocalDateTime.now()).forEach(s -> System.out.println(s));
        memberRepository.findByIdGreaterThan(3L).forEach(s -> System.out.println(s));
//        memberRepository.findByCreateAtLessThan(LocalDateTime.now()).forEach(System.out::println);
        memberRepository.findByIdGreaterThanEqualOrIdLessThanEqual(5L, 3L).forEach(System.out::println);
        memberRepository.findByNameLike("%순%").forEach(System.out::println);
        memberRepository.findByEmailLike("%@thejoeun.com%").forEach(System.out::println);
        memberRepository.findByNameIn(Lists.newArrayList("홍길동", "강감찬", "박남순")).forEach(System.out::println);
        memberRepository.findByNameContains("순").forEach(System.out::println);

        memberRepository.findByNameOrderByIdDesc("홍길동").forEach(System.out::println);
//        memberRepository.findByAddressIsEmpty().forEach(System.out::println);
//        memberRepository.findByAddressIsNotEmpty().forEach(System.out::println);
        memberRepository.findByMaleIsFalse().forEach(System.out::println);

        Page<Member> memberPage = memberRepository.findByName("홍길동", PageRequest.of(0, 3));
        List<Member> memberList = memberPage.getContent();
        memberList.forEach(System.out::println);
    }
}
