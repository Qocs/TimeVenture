package com.TimeVenture.review;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

/* reply 엔티티 클래스가 상속하는 추상 클래스
@MappedSuperclass : JPA 어노테이션, 부모클래스의 매핑 정보를 자식 엔티티 클래스들이
공통으로 매핑하는 정보 제공 -> 상속받는 reply 클래스들은 여기서 정의한 필드를 재사용할 수 있게 됨 */
@MappedSuperclass
@Getter
@Setter
@SuperBuilder //Lombok : 빌더 패턴을 자동으로 생성해주는데 상속 관계에서 해당 에너테이션으로 부모 클래스에서도 패턴 사용
@NoArgsConstructor //Lombok : 매개변수 없는 기본 생성자 생성
public class BaseReview {
    /* PK / @GeneratedValue(strategy = GenerationType.IDENTITY) :
    데이터베이스가 자동 증가 형식일 경우 사용(dbms가 자체적으로 식별자 관리 및 증가시키는 방식
    -> 해당 엔티티를 JPA가 저장 시 데이터베이스가 자동으로 증가하는 식별자 값을 할당 auto-increment 방식 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    //FK : 현재는 FK를 제거하고 부분 개발 중으로 FK관련 코드 제거
    private int tId; //task 테이블 FK
    private int mId; //member 테이블 FK

    //그 외 테이블 컬럼
    private String content;        //내용
    private Timestamp createdDate; //생성일(기본값:오늘일자시간)
    private Timestamp editedDate;  //수정일(기본값:오늘일자시간)

    /* 실제 컬럼명은 스네이크케이스로 DB에 저장되었으나, JPA 쿼리 메서드에서 네이밍 규칙을
    카멜로 지정하기 때문에 필드를 카멜스타일로 만들어도 별도의 @Column 으로 네임 명시 없이도
    자동으로 DBMS 의 컬럼과 매칭을 시켜줌
    * 정리
    1. 필드명과 DB 컬럼명이 완전히 일치 = 별도 매핑 지정 필요 없음
    2. 필드명이 카멜케이스, DB 컬럼명 스네이크 케이스 = 필드명에서 스네이크케이스를 카멜케이스로 변환하여 매핑
    3. 필드명과 DB 컬럼명이 불일치 =  @Column(name = "컬럼명") 을 통해 매핑 */
}
