package com.TimeVenture.task.doing;

import com.TimeVenture.task.BaseTaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoingTaskRepository extends BaseTaskRepository<DoingTask, Integer> {
}
