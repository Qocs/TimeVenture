package com.TimeVenture.review.todo;

import com.TimeVenture.review.BaseReviewController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//BaseReview 컨트롤러 상속받는 자식 컨트롤러 클래스
@RestController
@RequestMapping("/api/reviews")
public class TodoReviewController extends BaseReviewController<TodoReview, TodoReviewService> {

    public TodoReviewController(TodoReviewService reviewService) {
        super(reviewService);
    }

}
