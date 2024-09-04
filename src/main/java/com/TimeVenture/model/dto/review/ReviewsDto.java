package com.TimeVenture.model.dto.review;

import com.TimeVenture.model.entity.member.entity.Member;
import com.TimeVenture.model.entity.task.Task;
import lombok.Data;

import java.sql.Timestamp;

    @Data
    public class ReviewsDto {
        private int reviewId;
        private Task tid;
        private Member mid;
        private String content;
        private Timestamp createdDate;
    }

