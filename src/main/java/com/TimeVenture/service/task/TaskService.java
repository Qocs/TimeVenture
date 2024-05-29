package com.TimeVenture.service.task;

import com.TimeVenture.model.entity.task.Task;
import com.TimeVenture.model.enums.TaskSort;
import com.TimeVenture.model.enums.TaskStatus;
import com.TimeVenture.repository.task.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {
    protected final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // CREATE
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // READ
    // 정렬 기준
    private Sort getSortCriteria(TaskSort sort) {
        Sort sortby = null; // 정렬 기준 변수 초기화
        if(sort != null) {
            sortby = switch (sort) {
                case DUE_DATE_ASC -> Sort.by(Sort.Direction.ASC, "dueDate");
                case DUE_DATE_DESC -> Sort.by(Sort.Direction.DESC, "dueDate");
                case MEMBER_ASC -> Sort.by(Sort.Direction.ASC, "pmember");
                case MEMBER_DESC -> Sort.by(Sort.Direction.DESC, "pmember");
                case CREATED_DATE_ASC -> Sort.by(Sort.Direction.ASC, "createdDate");
                case CREATED_DATE_DESC -> Sort.by(Sort.Direction.DESC, "createdDate");
                case PRIORITY_ASC -> Sort.by(Sort.Direction.ASC, "priority");
                case PRIORITY_DESC -> Sort.by(Sort.Direction.DESC, "priority");
                default -> throw new IllegalArgumentException("Unsupported sort type: " + sort);
            };
        }
        return sortby;
    }
    // 모든 tasks 조회
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    // 모든 tasks 조회 + 정렬 기능 추가
    public List<Task> getTasksByPid(int pid, TaskStatus status, TaskSort sort) {
        return taskRepository.findByPidAndTaskStatus(pid, status, getSortCriteria(sort));
    }
    // 특정 task 내용 조회
    public Task getTaskById(int tId) {
        return taskRepository.findById(tId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with task_id: " + tId));
    }
    // 검색
    public List<Task> searchTitleOrderBy(String searchWord, Integer pid, Integer pmember, TaskSort sort, TaskStatus status) {
        return taskRepository.findByPidAndTaskStatusAndPmemberAndTitleContaining(pid, status, pmember, searchWord, getSortCriteria(sort));
    }

    /* UPDATE */
    public Task updateTask(int tid, Task updatedTask) {
        Task task = getTaskById(tid);
        Task newTask = task.toBuilder()
                .title(updatedTask.getTitle())
                .content(updatedTask.getContent())
                .priority(updatedTask.getPriority())
                .taskStatus(updatedTask.getTaskStatus())
                .dueDate(updatedTask.getDueDate())
                .build();
        return taskRepository.save(newTask);
    }

    /* DELETE */
    public void deleteTask(int tid) {
        Task task = getTaskById(tid);
        taskRepository.delete(task);
    }
    public void deleteTasksByPidAndTaskStatus(int pid, TaskStatus status) {
        List<Task> tasks = taskRepository.findByPidAndTaskStatus(pid, status, null);
        taskRepository.deleteAll(tasks);
    }

}
