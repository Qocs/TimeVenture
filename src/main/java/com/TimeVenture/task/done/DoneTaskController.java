package com.TimeVenture.task.done;

import com.TimeVenture.task.BaseTaskController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class DoneTaskController extends BaseTaskController<DoneTask, DoneTaskService> {
    public DoneTaskController(DoneTaskService taskService) {
        super(taskService);
    }
}
