package com.TimeVenture.task.todo;

import com.TimeVenture.task.BaseTaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TodoTaskService extends BaseTaskService<TodoTask, TodoTaskRepository> {
    public TodoTaskService(TodoTaskRepository taskRepository) {
        super(taskRepository);
    }
}
