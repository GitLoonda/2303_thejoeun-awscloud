package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import com.tj.edu.practice5.jpa.model.MemberLogHistory;
import com.tj.edu.practice5.jpa.model.enums.Nation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberLogHistoryRepositoryTest {
    @Autowired
    private MemberLogHistoryRepository memberLogHistoryRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void selectRelationTest1() throws InterruptedException {
        givenMember();
        givenMember2();

        List<MemberLogHistory> memberLogHistories
                = memberLogHistoryRepository.findAll();
        memberLogHistories.forEach(System.out::println);
        System.out.println(memberLogHistories.get(0).getMember());
    }

    @Test
    void selectRelationTest2() throws InterruptedException {
        givenMember();
        givenMember2();

        memberLogHistoryRepository.findByEmail("grasshopper@thejoeun.com").forEach(System.out::println);
    }


    private Member givenMember() throws InterruptedException {
        Member member = Member.builder()
                .name("유재석")
                .male(true)
                .email("grasshopper@thejoeun.com")
                .nation(Nation.KOREA)
                .build();

        Thread.sleep(1000);

        Member member2 = memberRepository.save(member);
        member2.setName("김종국");

        return memberRepository.save(member2);
    }

    private Member givenMember2() {
        Member member = Member.builder()
                .name("송지효")
                .male(false)
                .email("aceofspade@thejoeun.com")
                .nation(Nation.KOREA)
                .build();
        return memberRepository.save(member);
    }
}
