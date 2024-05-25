package com.TimeVenture.task;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public abstract class BaseTaskService<T extends BaseTask, R extends BaseTaskRepository<T, Integer>> {
    protected final R taskRepository;

    public BaseTaskService(R taskRepository) {
        this.taskRepository = taskRepository;
    }

    // CREATE
    public T createTask(T task) {
        return taskRepository.save(task);
    }

    // READ
    public List<T> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<T> getAllTasksByPId(int id) {
        return taskRepository.findAllByPId(id);
    }

    public T getTaskById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
    }

    // UPDATE
    public T updateTask(int id, T updatedTask) {
        T task = getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setContent(updatedTask.getContent());
        task.setPriority(updatedTask.getPriority());
        task.setDueDate(updatedTask.getDueDate());
        return taskRepository.save(task);
    }

    // DELETE
    public void deleteTask(int id) {
        T task = getTaskById(id);
        taskRepository.delete(task);
    }
}
