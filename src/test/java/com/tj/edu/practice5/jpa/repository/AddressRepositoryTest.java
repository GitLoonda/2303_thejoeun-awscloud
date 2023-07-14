package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Address;
import com.tj.edu.practice5.jpa.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member givenMember() {
        Member member = Member.builder()
                .name("하하")
                .email("hahaha@gmail.com")
                .build();

        return memberRepository.save(member);
    }

    private Address givenAddress(Member member) {
        Address address = Address.builder()
                .member(member)
                .zipcode("101-102")
                .build();

        return addressRepository.save(address);
    }

    @Test
    void AddressRepositoryTest() {
        Member member = givenMember();
        Address address = givenAddress(member);
        addressRepository.findAll();
        addressRepository.findById(8L);
    }

}
