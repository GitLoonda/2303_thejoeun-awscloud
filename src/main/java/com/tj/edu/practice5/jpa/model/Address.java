package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@ToString
@Entity
public class Address {
    @Id
    private Long id;
    private String zipcode;
}
