package com.TimeVenture.task;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseTaskController<T extends BaseTask, S extends BaseTaskService<T, ?>> {
    protected final S taskService;

    public BaseTaskController(S taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<T> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping
    public List<T> getAllTasksByPId(@PathVariable int pId) {
        return taskService.getAllTasksByPId(pId);
    }

    @GetMapping("/{id}")
    public T getTaskById(@PathVariable int tId) {
        return taskService.getTaskById(tId);
    }

    @PostMapping
    public T createTask(@RequestBody T task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public T updateTask(@PathVariable int id, @RequestBody T updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }
}