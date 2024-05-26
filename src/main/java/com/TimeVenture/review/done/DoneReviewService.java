package com.TimeVenture.review.done;

import com.TimeVenture.review.BaseReviewService;
import org.springframework.stereotype.Service;

/* BaseReviewService 에게 상속 받는 자식 service 클래스
부모 객체에 @Transaction 이 적용되어 하위 클래시인 이 클래스에도 적용됨 */
@Service
public class
DoneReviewService extends BaseReviewService<DoneReview, DoneReviewRepository> {
    public DoneReviewService(DoneReviewRepository reviewRepository){
        super(reviewRepository);
    }
}

