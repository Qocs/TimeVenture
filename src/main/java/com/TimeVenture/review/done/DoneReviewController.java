package com.TimeVenture.review.done;

import com.TimeVenture.review.BaseReviewController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//BaseReview 컨트롤러 상속받는 자식 컨트롤러 클래스
@RestController
@RequestMapping("/api/done-reviews")
public class DoneReviewController extends BaseReviewController<DoneReview, DoneReviewService> {

    public DoneReviewController(DoneReviewService reviewService) {
        super(reviewService);
    }

}
