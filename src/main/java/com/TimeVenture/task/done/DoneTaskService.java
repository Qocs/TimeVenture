package com.TimeVenture.task.done;

import com.TimeVenture.task.BaseTaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DoneTaskService extends BaseTaskService<DoneTask, DoneTaskRepository> {
    public DoneTaskService(DoneTaskRepository taskRepository) {
        super(taskRepository);
    }
}
