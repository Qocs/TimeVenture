package com.TimeVenture.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseTaskRepository<T extends BaseTask, ID> extends JpaRepository<T, ID> {
}
