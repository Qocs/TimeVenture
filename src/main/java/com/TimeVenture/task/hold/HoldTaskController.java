package com.TimeVenture.task.hold;

import com.TimeVenture.task.BaseTaskController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class HoldTaskController extends BaseTaskController<HoldTask, HoldTaskService> {
    public HoldTaskController(HoldTaskService taskService) {
        super(taskService);
    }
}
