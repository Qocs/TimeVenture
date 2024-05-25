package com.TimeVenture.review;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseReviewRepository<T extends BaseReview, ID> extends JpaRepository<T, ID> {

/*    @Query("SELECT t FROM #{#entityName} t WHERE t.tId = :id")
    List<T> findAllByTId(@Param("id") int id);*/
}
