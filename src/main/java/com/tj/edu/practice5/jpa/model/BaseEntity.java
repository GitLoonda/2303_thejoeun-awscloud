package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass   // 상속할 Auditing 요소들이 superclass의 요소라는 것을 명시해야 함
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements TimeAuditable {

    @CreatedDate            // AudtingEntityListner Annotation
    private LocalDateTime createAt;

    @LastModifiedDate       // AudtingEntityListner Annotation
    private LocalDateTime updateAt;

    @CreatedBy
    private Long createBy;

    @LastModifiedBy
    private Long updateBy;
}
