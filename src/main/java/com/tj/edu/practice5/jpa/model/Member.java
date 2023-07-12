package com.tj.edu.practice5.jpa.model;


import com.tj.edu.practice5.jpa.model.enums.Nation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(columnNames = {"colTest2"})})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NonNull
    @Column(columnDefinition = "varchar(20) comment '이름'", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(100) comment '이메일'", unique = true)
    private String email;

    @Column(name = "colTest2", unique = true)
    private Integer test2;
//    @NonNull
    @Column(columnDefinition = "datetime(6) DEFAULT now() comment '작성시간'", updatable = false)
    private LocalDateTime createAt;

    @Column(columnDefinition = "datetime(6) DEFAULT now() comment '수정시간'", insertable = false, nullable = false)
    private LocalDateTime updateAt;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;

    @Column(nullable = true)
    private boolean male;

    @Transient
    private String testData;

    @Column(columnDefinition = "varchar(20)", nullable = true)
    @Enumerated(value = EnumType.STRING)
    private Nation nation;
}