package com.TimeVenture.review;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;

@Transactional
public abstract class BaseReviewService<T extends BaseReview, R extends BaseReviewRepository<T, Integer>> {
    /* protected 사용 : reviewRepository 는 현 서비스를 상속 받는 하위클래스에만 접근을 허용
    하위클래스에서만 특정 리포지토리 객체를 사용하도록 강제(공통적으로 사용하는 로직을 정의하고 재사용) */
    protected final R reviewRepository;

    public BaseReviewService(R reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    //CREATE - 댓글 추가
    public T createReview(T review){
        return reviewRepository.save(review);
    }

    //READ
    //전체 글 조회
    public List<T> getAllReviews() {
        return reviewRepository.findAll();
    }

    //UPDATE - 댓글 수정
    public T updateReview(int id, T updatedReview) {
        /* findById 메서드는 Optional<T>를 반환
        Optional 값이 없을 경우 orElseThrow 메서드를 이용해 런타임 익셉션 throw */
        T existingReview = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        existingReview.setContent(updatedReview.getContent());
        existingReview.setEditedDate(new Timestamp(System.currentTimeMillis()));
        return reviewRepository.save(existingReview);
    }

    //DELETE - 댓글 삭제
    public void deleteReview(int id){
        reviewRepository.deleteById(id);
    }
}
