package com.tj.edu.practice5.jpa.model;

import com.tj.edu.practice5.jpa.repository.MemberLogHistoryRepository;
import com.tj.edu.practice5.jpa.util.SpringBeanUtils;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class MemberEntityListener {

    @PostPersist
    @PostUpdate
    public void PostInsertMember(Object obj) {
        MemberLogHistoryRepository memberLogHistoryRepository = SpringBeanUtils.getBean(MemberLogHistoryRepository.class);

        Member member = (Member) obj;
        MemberLogHistory memberLogHistory = MemberLogHistory.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();

        memberLogHistoryRepository.save(memberLogHistory);
    }


}
