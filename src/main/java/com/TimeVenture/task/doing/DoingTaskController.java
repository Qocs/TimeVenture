package com.TimeVenture.task.doing;

import com.TimeVenture.task.BaseTaskController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class DoingTaskController extends BaseTaskController<DoingTask, DoingTaskService> {
    public DoingTaskController(DoingTaskService taskService) {
        super(taskService);
    }
}
