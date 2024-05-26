package com.TimeVenture.review.doing;

import com.TimeVenture.review.BaseReviewController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//BaseReview 컨트롤러 상속받는 자식 컨트롤러 클래스
@RestController
@RequestMapping("/api/doing-reviews")
public class DoingReviewController extends BaseReviewController<DoingReview, DoingReviewService> {

    public DoingReviewController(DoingReviewService reviewService) {
        super(reviewService);
    }

}
