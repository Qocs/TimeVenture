package com.TimeVenture.review.hold;

import com.TimeVenture.review.BaseReviewController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//BaseReview 컨트롤러 상속받는 자식 컨트롤러 클래스
@RestController
@RequestMapping("/api/hold-reviews")
public class HoldReviewController extends BaseReviewController<HoldReview, HoldReviewService> {

    public HoldReviewController(HoldReviewService reviewService) {
        super(reviewService);
    }

}
