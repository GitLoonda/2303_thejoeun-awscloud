package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import com.tj.edu.practice5.jpa.model.Users;
import com.tj.edu.practice5.lombok.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class EntityManagerTest {
//    @Autowired
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Test
    void entityManagerTest() {
        em.createQuery("select u from Member u").getResultList().forEach(s -> System.out.println(s));
    }

    @Test
//    @Transactional
    void cacheEntityFindTest() {
//        System.out.println(memberRepository.findByEmail("mars@thejoeun.com"));
//        System.out.println(memberRepository.findByEmail("mars@thejoeun.com"));
//        System.out.println(memberRepository.findByEmail("mars@thejoeun.com"));
//        System.out.println(memberRepository.findById(1L));
//        System.out.println(memberRepository.findById(1L));
//        System.out.println(memberRepository.findById(1L));
        memberRepository.deleteById(1L);
    }

    @Test
    @Transactional
    void cacheEntityFindTest2() {
        Member member = memberRepository.findById(1L).get();
        member.setName("아레스");

        memberRepository.save(member);

        member.setEmail("ares@greece.com");

//        memberRepository.save(member);
//        memberRepository.flush();
        memberRepository.saveAndFlush(member);
    }

    @Test
    @Transactional  // OSIV (Open Session In View) -> Transaction
    void entityManageTest2() {
        Member member = Member.builder()
                .name("테스트")
                .email("test@test.com")
                .build();
        em.persist(member); // JPA 영속성(Persistence)

        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    @Transactional
    @Commit
//    @Rollback(false)  // default
    void entityManageTest3() {
        Users users = Users.builder()
                .name("테스트")
                .build();

        em.persist(users);  // 영속성 상태(1차 캐시 저장)
        users.setEmail("test1@test.com");
//        em.detach(users); // 준영속성 상태(commit 반영되지 않음)
//        users.setEmail("test1@test.com");
//        em.merge(users);  // 준영속성 상태에서 영속성 상태로 변경
        em.remove(users); // 비영속성 상태(1차 캐시 삭제)
//        em.flush();

        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    @Transactional
    @Commit
    void persistCacheDelayInsertUpdateTest() {
        Users user = usersRepository.findById(1L).get(); // select, dirty check
        user.setName("테스트1");
        usersRepository.save(user); // update
//        user.setEmail("test1@abc.com");
//        usersRepository.save(user); // update
    }

}
