package com.TimeVenture.model.dto.review;

import lombok.Data;

import java.sql.Timestamp;

    @Data
    public class ReviewsDto {
        private int reviewId;
        private int tid;
        private String mid;
        private String content;
        private Timestamp createdDate;
    }

