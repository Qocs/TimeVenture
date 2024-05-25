package com.TimeVenture.task.hold;

import com.TimeVenture.task.BaseTaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HoldTaskService extends BaseTaskService<HoldTask, HoldTaskRepository> {
    public HoldTaskService(HoldTaskRepository taskRepository) {
        super(taskRepository);
    }
}
