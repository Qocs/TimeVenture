package com.TimeVenture.service.task;

import com.TimeVenture.model.TaskModelMapper;
import com.TimeVenture.model.dto.task.CreateTaskRequestDto;
import com.TimeVenture.model.dto.task.ResponseTaskDto;
import com.TimeVenture.model.dto.task.UpdateTaskRequestDto;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.model.entity.projectMember.ProjectMember;
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
    protected final TaskModelMapper taskModelMapper;

    public TaskService(TaskRepository taskRepository, TaskModelMapper taskModelMapper) {
        this.taskRepository = taskRepository;
        this.taskModelMapper = taskModelMapper;
    }

    // CREATE
    public ResponseTaskDto createTask(CreateTaskRequestDto requestDto) {
        Task task = Task.builder()
                .pid(requestDto.getPid())
                .mid(requestDto.getMid())
                .pmember(requestDto.getPmember())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .priority(requestDto.getPriority())
                .taskStatus(requestDto.getTaskStatus())
                .build();
        Task createdTask = taskRepository.save(task);
        return taskModelMapper.toResponseDto(createdTask);
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
    public List<ResponseTaskDto> getAllTasks() {
        return taskModelMapper.toResponseDtoList(taskRepository.findAll());
    }
    public List<ResponseTaskDto> getTasksByPid(Project pid) {
        return taskModelMapper.toResponseDtoList(taskRepository.findByPid(pid));
    }
    // 특정 task 내용 조회
    public ResponseTaskDto getTaskById(int tId) {
        Task task = taskRepository.findById(tId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with task_id: " + tId));
        return taskModelMapper.toResponseDto(task);
    }
    // 검색
    public List<ResponseTaskDto> searchTitleOrderBy(String searchWord, Project pid, ProjectMember pmember, TaskSort sort, TaskStatus status) {
        return taskModelMapper.toResponseDtoList(taskRepository.findByPidAndTaskStatusAndPmemberAndTitleContaining(pid, status, pmember, searchWord, getSortCriteria(sort)));
    }

    /* UPDATE */
    public ResponseTaskDto updateTask(int tid, UpdateTaskRequestDto requestDto) {
        Task task = taskRepository.findById(tid)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with task_id: " + tid));
        Task updatedTask = task.toBuilder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .priority(requestDto.getPriority())
                .taskStatus(requestDto.getTaskStatus())
                .dueDate(requestDto.getDueDate())
                .build();
        return taskModelMapper.toResponseDto(taskRepository.save(updatedTask));
    }

    /* DELETE */
    public void deleteTask(int tid) {
        Task task = taskRepository.findById(tid)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with task_id: " + tid));
        taskRepository.delete(task);
    }
    public void deleteTasksByPidAndTaskStatus(Project pid, TaskStatus status) {
        List<Task> tasks = taskRepository.findByPidAndTaskStatus(pid, status, null);
        taskRepository.deleteAll(tasks);
    }

}