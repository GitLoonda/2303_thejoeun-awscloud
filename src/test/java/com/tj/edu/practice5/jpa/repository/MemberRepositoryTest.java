package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import com.tj.edu.practice5.jpa.model.MemberLogHistory;
import com.tj.edu.practice5.jpa.model.enums.Nation;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberLogHistoryRepository memberLogHistoryRepository;

    private Member getMember() {
        Member member = Member.builder()
                .name("유재석")
                .male(true)
                .email("grasshopper@thejoeun.com")
                .nation(Nation.KOREA)
                .build();
        return memberRepository.save(member);
    }

    @Test
    void crud() {
//        Member member1 = new Member(1L, "홍길동", null, LocalDateTime.now(), LocalDateTime.now());
        // insert문
//        Member copyMember = memberRepository.save(member1); // insert into Member values (~~~
//        System.out.println("copyMember -> " + copyMember);

//        Member member =
        // select all문
        System.out.println("select all문--------------------------------------------------------");
        List<Member> memberList = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "name")); // == select * from member
        // jdk 1.8에서 사용된 stream기술을 이용한 print찍는 방법
        memberList.forEach(System.out::println);
//        for(Member member : memberList) {
//            System.out.println(member.toString());
//        }

        // select where문
        System.out.println("select where문 --------------------------------------------------------");
        List<Member> memberList2 = memberRepository.findAllById(Lists.newArrayList(1L, 3L, 5L)); // == select * from member where id in (1, 3)
        memberList2.forEach(System.out::println);

        // update문
        System.out.println("update문 --------------------------------------------------------");
//        Member member1 = new Member(1L, "홍길동", "이메일 주소", LocalDateTime.now(), LocalDateTime.now(), null, true);
        Member member1 = Member.builder()
                        .id(1L)
                        .name("홍길동")
                        .email("이메일 주소")
//                        .createAt(LocalDateTime.now())
                        .build();
        memberRepository.save(member1);     // 1번을 가진 id가 있다면 update, 없으면 create문 발생
        List<Member> memberList3 = memberRepository.findAll();
        memberList3.forEach(System.out::println);

        // delete문
//        System.out.println("delete문 --------------------------------------------------------");
//        memberRepository.deleteAll();
//        memberRepository.deleteAllInBatch();
//        List<Member> memberList4 = memberRepository.findAll();
//        memberList4.forEach(System.out::println);
    }

    @Test
    void crud2() {
        // create문 (name과 createAt이 null이 아닌 insert문)
        Member member = Member.builder()
                        .name("조세호")
//                        .createAt(LocalDateTime.now())
                        .build();

        memberRepository.save(member);

        // create문 (updateAt이 null이 아닌 insert문)
        Member member2 = Member.builder()
                        .name("유재석")
//                        .createAt(LocalDateTime.now())
//                        .updateAt(LocalDateTime.now())
                        .build();

        memberRepository.save(member2);

        // create문 (id:15, name:박조은, email:parkjoeun@gmail.com, createAt:now())
//        Member member3 = Member.builder()
//                .id(15L)
//                .name("박조은")
//                .email("parkjoeun@gmail.com")
//                .createAt(LocalDateTime.now())
//                .build();
//        Member member3 = new Member(15L, "박조은", "parkjoeun@gmail.com", LocalDateTime.now(), null, null, false);
        Member member3 = Member.builder()
                .id(15L)
                .name("박조은")
                .email("parkjoeun@gmail.com")
//                .createAt(LocalDateTime.now())
                .build();
        memberRepository.save(member3);

        // read문 (id : 10 인 tuple을 가져오는 select문 / 없을 경우 런타임 에러 출력)
//        Optional<Member> memberOptional = memberRepository.findById(10L);
//        memberOptional.orElseThrow(RuntimeException::new);
//        System.out.println(memberOptional);
        Member member4 = memberRepository.findById(10L).orElse(null);
        if(member4 != null) {
            System.out.println(member4);
        }

        // read문 (id : 3 or 7인 tuple을 가져오는 select문)
//        List<Long> selector = Arrays.asList(3L, 7L);
//        List<Member> memberOptional1 = memberRepository.findAllById(selector);
//        memberOptional1.forEach(System.out::println);
        List<Member> listMember = memberRepository.findAllById(Lists.newArrayList(3L, 7L));
        listMember.forEach(System.out::println);

        // select문  count함수
        System.out.println("회원 수는 " + memberRepository.count() + " 명 입니다.");

        // select문 exist함수
        boolean isFiveNumberMember = memberRepository.existsById(5L);
        System.out.println("5번 회원 존재 여부 : " + isFiveNumberMember);

        boolean isNinetyNumberMember = memberRepository.existsById(90L);
        System.out.println("90번 회원 존재 여부 : " + isNinetyNumberMember);

        // select문 page함수
        Page<Member> members = memberRepository.findAll(PageRequest.of(1, 3));
        members.forEach(System.out::println);
        System.out.println("page : " + members);
        System.out.println("totalElements : " + members.getTotalElements());
        System.out.println("totalPage : " + members.getTotalPages());
        System.out.println("numberOfElements : " + members.getNumberOfElements());
        System.out.println("sort : " + members.getSort());
        System.out.println("size : " + members.getSize());

        List<Member> memberList2 = members.getContent();
        memberList2.forEach(System.out::println);

        // select문 (jpa find example 이용)
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());

        Example<Member> memberExample = Example.of(
                Member.builder()
//                        .name("박남순")
                        .email("@thejoeun.com")
                        .build(),
                matcher
        );
        memberRepository.findAll(memberExample).forEach(System.out::println);
    }

    @Test
    void jpaSchemaTest() throws InterruptedException {
        Member member = Member.builder()
                .name("정준하")
                .email("juna@thejoeun.com")
//                .createAt(LocalDateTime.now())
//                .updateAt(LocalDateTime.now())
                .build();
        memberRepository.saveAndFlush(member);      // insert

        Thread.sleep(500);

        member.setName("박명수");
        member.setMale(true);
        member.setEmail("gashwal@gmail.com");
        member.setUpdateAt(LocalDateTime.now());
        memberRepository.saveAndFlush(member);      // update
    }

    @Test
    void jpaEnumTest() {
        Member member = Member.builder()
                .name("노홍철")
                .male(true)
                .email("redsteel@thejoeun.com")
//                .createAt(LocalDateTime.now())
//                .updateAt(LocalDateTime.now())
                .nation(Nation.KOREA)
                .build();
        memberRepository.saveAndFlush(member);
    }

    @Test
    void jpaEventListenerTest() {
        Member member = Member.builder()
                .name("유재석")
                .male(true)
                .email("mettugi@thejoeun.com")
//                .createAt(LocalDateTime.now())
//                .updateAt(LocalDateTime.now())
                .nation(Nation.KOREA)
                .build();
        memberRepository.saveAndFlush(member);  // insert(PrePersist, PostPersist)

        Member member1 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member1.setName("마이콜");
        memberRepository.save(member1);         // update(PreUpdate, PostUpdate)

        memberRepository.deleteById(2L);        // delete(PreRemove, PostRemove)
    }

    @Test
    void memberLogHistroyTest() {
        Member member = Member.builder()
                .name("유재석")
                .male(true)
                .email("grasshopper@thejoeun.com")
//                .createAt(LocalDateTime.now())
//                .updateAt(LocalDateTime.now())
                .nation(Nation.KOREA)
                .build();
        memberRepository.save(member);

        Member member1 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member1.setName("마이콜");
        memberRepository.save(member1);
    }

    @Test
    void oneToManyTest() {
        Member member = getMember();
        member.setName("김종국");
        memberRepository.save(member);

        List<MemberLogHistory> memberLogHistories
                = memberRepository.findByEmail("grasshopper@thejoeun.com").getMemberLogHistories();
        memberLogHistories.forEach(System.out::println);

//        List<Member> memberList = memberRepository.findAll();
//        Member member2 = memberList.get(7);
//        List<MemberLogHistory> memberLogHistories = member2.getMemberLogHistories();
//        System.out.println(">>>" + memberLogHistories);
//        List<MemberLogHistory> memberLogHistories = memberLogHistoryRepository.findByMemberId(member.getId());
//        Optional<Member> member2 = memberRepository.findById(memberLogHistories.get(0).getMemberId());
//        System.out.println();
    }

    @Test
    void emptyTest() {

    }
}