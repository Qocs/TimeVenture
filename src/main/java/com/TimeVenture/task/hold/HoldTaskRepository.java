package com.TimeVenture.task.hold;

import com.TimeVenture.task.BaseTaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldTaskRepository extends BaseTaskRepository<HoldTask, Integer> {
}
