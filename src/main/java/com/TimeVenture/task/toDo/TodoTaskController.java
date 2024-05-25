package com.TimeVenture.task.todo;

import com.TimeVenture.task.BaseTaskController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TodoTaskController extends BaseTaskController<TodoTask, TodoTaskService> {
    public TodoTaskController(TodoTaskService taskService) {
        super(taskService);
    }
}
