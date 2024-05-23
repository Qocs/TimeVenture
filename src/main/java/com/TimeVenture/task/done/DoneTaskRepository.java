package com.TimeVenture.task.done;

import com.TimeVenture.task.BaseTaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoneTaskRepository extends BaseTaskRepository<DoneTask, Integer> {
}
