package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Board {
    @Id
    private String boardNo;
    private String boardKind;
    private String keywordType;
    private String userId;
    private String cdatetime;
    private String udatetime;
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
