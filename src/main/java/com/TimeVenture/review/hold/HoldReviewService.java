package com.TimeVenture.review.hold;

import com.TimeVenture.review.BaseReviewService;
import org.springframework.stereotype.Service;

/* BaseReviewService 에게 상속 받는 자식 service 클래스
부모 객체에 @Transaction 이 적용되어 하위 클래시인 이 클래스에도 적용됨 */
@Service
public class
HoldReviewService extends BaseReviewService<HoldReview, HoldReviewRepository> {
    public HoldReviewService(HoldReviewRepository reviewRepository){
        super(reviewRepository);
    }
}

