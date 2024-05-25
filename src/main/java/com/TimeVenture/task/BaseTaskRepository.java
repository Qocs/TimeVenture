package com.TimeVenture.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface BaseTaskRepository<T extends BaseTask, ID> extends JpaRepository<T, ID>{

    // #{#entityName} : Spring Data JPA 에서 제공하는 SpEL(Spring Expression Language)의 표현식. T로 대체된다.: Spring Data JPA 에서 제공하는 SpEL(Spring Expression Language)의 표현식. T로 대체된다.
    @Query("SELECT t FROM #{#entityName} t WHERE t.pId = :id")
    List<T> findAllByPId(@Param("id") int id);
}
