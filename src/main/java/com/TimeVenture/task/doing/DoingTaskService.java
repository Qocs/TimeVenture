package com.TimeVenture.task.doing;

import com.TimeVenture.task.BaseTaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DoingTaskService extends BaseTaskService<DoingTask, DoingTaskRepository> {
    public DoingTaskService(DoingTaskRepository taskRepository) {
        super(taskRepository);
    }
}
