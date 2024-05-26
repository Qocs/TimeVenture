package com.TimeVenture.review.doing;

import com.TimeVenture.review.BaseReviewRepository;
import org.springframework.stereotype.Repository;


/* ★ BaseReviewRepository / 자식 ReviewRepository 둘 다 인터페이스이므로
상속 관계 시 자식클래스에서 extends 사용

- 상속 받는 자식객체의 인터페이스는 @Repository 에너테이션을 이용하여 실제로 해당 역할을 수행하는 것을
명시 함 */
@Repository
interface DoingReviewRepository extends BaseReviewRepository<DoingReview, Integer> {
}