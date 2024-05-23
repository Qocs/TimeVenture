package com.TimeVenture.task.toDo;

import com.TimeVenture.task.BaseTaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTaskRepository extends BaseTaskRepository<TodoTask, Integer> {
}
