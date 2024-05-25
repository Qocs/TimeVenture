package com.TimeVenture.review;

import com.TimeVenture.task.BaseTask;
import com.TimeVenture.task.BaseTaskRepository;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public abstract class BaseReviewService <T extends BaseReview, R extends BaseReviewRepository<T, Integer>>{
    /* protected 사용 : reviewRepository 는 현 서비스를 상속 받는 하위클래스에만 접근을 허용
    하위클래스에서만 특정 리포지토리 객체를 사용하도록 강제(공통적으로 사용하는 로직을 정의하고 재사용) */
    protected final R reviewRepository;

    public BaseReviewService(R reviewRepository){
        this.reviewRepository = reviewRepository;
    }
    //READ
    //전체 글 조회
    public List<T> getAllReviews() {
        return reviewRepository.findAll();
    }
}
