package com.TimeVenture.review;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

//ReviewDTO 클래스들의 부모 클래스
@Getter
@Setter
public class BaseReviewDto {

    private int reviewId;           //PK
    private int tId;                //Tasks 테이블 식별키(FK)
    private int mId;                //Member 테이블 식별키(FK)
    private String content;         //댓글 내용
    private Timestamp createdDate;  //생성일 (기본값 지금 일자,시간)
    private Timestamp editedDate;   //수정일 (기본값 지금 일자,시간)
}