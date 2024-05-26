package com.TimeVenture.review;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/* 다른 ReviewController 가 상속 받는 부모 클래스
이후 다른 자식 Review 가 추가될 경우 새로운 엔티티, 서비스 클래스에 대한 제네릭을 지정하여 용이하게 추가 구현 가능
T extends BaseReview : BaseReview 를 상속 받는 엔티티 객체
S extends BaseReviewService<T,?> : 상기 T와 와일드카드(<?>)를 이용하여, BaseReviewRepository를 상속하는
어떤 Repository(여기선 done,doing 등.. 자식객체에 따라 변동되는 부분)가 와도 유연하게 적용되며,
BaseReviewService 를 상속 받는 서비스 객체 */
public class BaseReviewController<T extends BaseReview, S extends BaseReviewService<T,?>> {
    /* protected 사용 : reviewService 는 현 컨트롤러를 상속 받는 하위클래스에만 접근을 허용
    하위클래스에서만 특정 서비스 객체를 사용하도록 강제(공통적으로 사용하는 로직을 정의하고 재사용) */
    protected final S reviewService;

    //외부에서 리뷰서비스 주입
    public BaseReviewController(S reviewService){
        this.reviewService = reviewService;
    }
    //CREATE - 게시글 생성
    @PostMapping
    public T createReview(@RequestBody T review){
    /* @RequestBody : HTTP 요청의 본문을 Java 객체로 변환할 때 사용
    클라이언트가 서버로 데이터를 보낼 때(POST, PUT 등) 요청 본문을 Java 객체로 변환 */
        review.setTId(1); //FK 설정 전으로 임시로 1 값 입력
        review.setMId(1); //FK 설정 전으로 임시로 1 값 입력
        return reviewService.createReview(review);
    }

    //READ
    //1. 전체 글 조회
    @GetMapping
    public List<T> getAllReviews() {
        return reviewService.getAllReviews();
    }

    //UPDATE - 댓글 내용 수정
    @PutMapping("/{id}")
    public T updateReview(@PathVariable("id") int id, @RequestBody T review) {
        return reviewService.updateReview(id, review);
    }

    //DELETE - 댓글 삭제
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable("id") int id) {
        reviewService.deleteReview(id);
    }
}

