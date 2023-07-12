package com.tj.edu.practice5.jpa.model;


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
@Table(name = "memberCopy")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NonNull
    private String name;
    private String email;
//    @NonNull
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;
    private boolean male;
}