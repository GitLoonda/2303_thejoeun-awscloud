package com.tj.edu.practice5.jpa.model;


import com.tj.edu.practice5.jpa.model.enums.Nation;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(value = {/*AuditingEntityListener.class,*/ MemberEntityListener.class})
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NonNull 혹은 @Column(nullable = false)
    @Column(columnDefinition = "varchar(20) comment '이름'", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(100) comment '이메일'", unique = true)
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;

    @Column(columnDefinition = "boolean DEFAULT false comment '성별'")
    private boolean male;

    @Transient  // DB에 반영되지 않는 데이터
    private String testData;

    @Column(columnDefinition = "varchar(20)", nullable = true)
    @Enumerated(value = EnumType.STRING)
    private Nation nation;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<MemberLogHistory> memberLogHistories;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<Review> reviews;

    @PrePersist
    public void preInsert1() {
        System.out.println(">>> Memeber Insert 전");
    }
    @PreUpdate
    public void preUpdate1() {
        System.out.println(">>> Member Update 전");
    }
    @PreRemove
    public void preDelete1() {
        System.out.println(">>> Member Delete 전");
    }
    @PostPersist
    public void afterInsert1() {
        System.out.println(">>> Member Insert 후");
    }
     @PostUpdate
     public void afterUpdate1() {
         System.out.println(">>> Member Update 후");
     }
     @PostRemove
     public void afterDelete1() {
         System.out.println(">>> Member Delete 후");
     }
     @PostLoad
     public void afterSelect1() {
         System.out.println(">>> Member Select 후");
     }
}