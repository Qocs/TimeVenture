package com.TimeVenture.service.review;

import com.TimeVenture.model.ReviewsModelMapper;
import com.TimeVenture.model.dto.review.ReviewsDto;
import com.TimeVenture.model.entity.review.Reviews;
import com.TimeVenture.model.entity.task.Task;
import com.TimeVenture.repository.review.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final ReviewsModelMapper reviewsMapper;

    @Autowired
    public ReviewsService(ReviewsRepository reviewsRepository, ReviewsModelMapper reviewsMapper){
        this.reviewsRepository = reviewsRepository;
        this.reviewsMapper = reviewsMapper;
    }

    //CREATE : 댓글 추가
    public ReviewsDto addReviews(ReviewsDto reviewsDto) {
        Reviews reviews = reviewsMapper.toReviews(reviewsDto);
        reviews.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Reviews saveReviews = reviewsRepository.save(reviews);
        return reviewsMapper.toDto(saveReviews);
    }

    // READ : 댓글 전체 조회(test 용)
    /*public List<ReviewsDto> getAllReviews() {
        List<Reviews> reviews = reviewsRepository.findAll();
        return reviews.stream().map(reviewsMapper::toDto).toList() } */
    // READ : tasks 별 댓글 조회 (Reviews by Task ID)
    public List<ReviewsDto> getReviewsByTid(Task tid) {
        List<Reviews> reviews = reviewsRepository.findByTid(tid);
        /* reviews.stream() : List를 Stream으로 변환
        map(reviewsMapper::toDto) : Reviews 를 ReviewsDto 객체로 변환하는 메서드 참조 / map : 각 요소를 변환하여 새로운 스트림 생성
        > Reviews (엔티티) 객체가 ReviewsDto 객체로 변환
        toList() : 변환된 스트림을 다시 리스트로 변환 */
        return reviews.stream().map(reviewsMapper::toDto).toList();
    }

    // UPDATE : 댓글 수정
    public ReviewsDto updateReviews(int reviewId, ReviewsDto reviewsDto){
        //댓글 존재 여부 확인(reviewId를 통해) 후 존재하지 않을 경우 런타임 익셉션 던짐
        Reviews existingReviews = reviewsRepository.findById(reviewId).orElseThrow(()
                -> new RuntimeException("댓글이 없습니다"));
        //존재할 경우 세팅
        existingReviews.setContent(reviewsDto.getContent());
        existingReviews.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        Reviews updateReviews = reviewsRepository.save(existingReviews);
        return reviewsMapper.toDto(updateReviews);
    }

    // DELETE : 댓글 삭제
    public void deleteReviews(int reviewId) {
        reviewsRepository.deleteById(reviewId);
    }

}