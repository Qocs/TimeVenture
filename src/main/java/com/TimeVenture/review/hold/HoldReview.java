package com.TimeVenture.review.hold;

import com.TimeVenture.review.BaseReview;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Hold_reviews")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class HoldReview extends BaseReview {
    //BaseReview 를 상속 받는 자식 엔티티 클래스
}
