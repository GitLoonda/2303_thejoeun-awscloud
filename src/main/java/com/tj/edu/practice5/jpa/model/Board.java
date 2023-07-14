package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class Board extends BaseEntity {
    @Id
    private String boardNo;
    private String boardKind;
    private String keywordType;
    private String userId;
    private String title;
    private String content;
    private String viewCnt;
    private String delYn;
    private String replyYn;
    //	T1_BRDIMG 부분
    private String imgNo;
    //	private String boardNo;
    private String replyNo;
    private String imgSrc;
    private String imgName;
    private String orgName;
    private String imgType;
}
