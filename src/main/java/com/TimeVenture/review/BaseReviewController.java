package com.TimeVenture.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    //조회
    @GetMapping
    public List<T> getAllReviews() {
        return reviewService.getAllReviews();
    }

}
