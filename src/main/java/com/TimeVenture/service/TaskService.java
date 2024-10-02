package com.TimeVenture.service.task;

import com.TimeVenture.model.TaskModelMapper;
import com.TimeVenture.model.dto.task.CreateTaskRequestDto;
import com.TimeVenture.model.dto.task.ResponseTaskDto;
import com.TimeVenture.model.dto.task.UpdateTaskRequestDto;
import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.model.entity.projectMember.ProjectMember;
import com.TimeVenture.model.entity.task.Task;
import com.TimeVenture.model.enums.TaskSort;
import com.TimeVenture.model.enums.TaskStatus;
import com.TimeVenture.repository.member.MemberRepository;
import com.TimeVenture.repository.project.ProjectRepository;
import com.TimeVenture.repository.projectMember.ProjectMemberRepository;
import com.TimeVenture.repository.task.TaskRepository;
import com.TimeVenture.service.member.MemberStringService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@Transactional
public class TaskService {
    protected final TaskRepository taskRepository;
    protected final ProjectRepository projectRepository;
    protected final MemberRepository memberRepository;
    protected final ProjectMemberRepository projectMemberRepository;
    protected final TaskModelMapper taskModelMapper;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, TaskModelMapper taskModelMapper, MemberStringService memberService, MemberRepository memberRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.taskModelMapper = taskModelMapper;
        this.memberRepository = memberRepository;
    }

    // CREATE
    public ResponseTaskDto createTask(CreateTaskRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getPid())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Member member = memberRepository.findByEmail(requestDto.getMid())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Task task = requestDto.toEntity(project, member);
        Task savedTask = taskRepository.save(task);
        return TaskModelMapper.INSTANCE.toResponseDto(savedTask);
    }

    // READ
    // 정렬 기준
    private Sort getSortCriteria(TaskSort sort, String fieldName) {
        if (sort == null || fieldName == null || fieldName.isEmpty()) {
            return null;  // 유효하지 않은 입력에 대해 null 반환
        }

        return switch (sort) {
            case ASC -> Sort.by(ASC, fieldName);
            case DESC -> Sort.by(DESC, fieldName);
            default -> throw new IllegalArgumentException("Unsupported sort type: " + sort);
        };
    }
    // 모든 tasks 조회
    public List<ResponseTaskDto> getAllTasks() {
        return taskModelMapper.toResponseDtoList(taskRepository.findAll());
    }
    public List<ResponseTaskDto> getTasksByPid(Project pid) {
        return taskModelMapper.toResponseDtoList(taskRepository.findByPid(pid));
    }
    public List<ResponseTaskDto> getTasksByMid(Member mid) {
        return taskModelMapper.toResponseDtoList(taskRepository.findByMid(mid));
    }
    // 특정 task 내용 조회
    public ResponseTaskDto getTaskById(int tId) {
        Task task = taskRepository.findById(tId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with task_id: " + tId));
        return taskModelMapper.toResponseDto(task);
    }
    public List<ResponseTaskDto> searchTitleOrderBy(String searchWord, Project project, ProjectMember pmember, TaskSort sort, TaskStatus status) {
        // 필드명을 동적으로 결정하는 로직
        String sortField = getSortField(sort);

        // 동적으로 정렬 기준을 설정
        Sort sortCriteria = getSortCriteria(sort, sortField);

        // 검색 조건에 따라 다르게 처리
        if (project == null && pmember == null && status == null) {
            return taskModelMapper.toResponseDtoList(taskRepository.findByTitleContaining(searchWord, sortCriteria));
        } else {
            return taskModelMapper.toResponseDtoList(taskRepository.findByTitleContainingAndPidAndTaskStatusAndPmember(
                    searchWord,
                    project != null ? project : null,
                    status != null ? status : null,
                    pmember != null ? pmember : null,
                    sortCriteria
            ));
        }
    }

    // 필드명을 동적으로 결정하는 메서드
    private String getSortField(TaskSort sort) {
        if (sort == null) {
            return "createdDate";  // 기본값 설정
        }

        // TaskSort에 맞는 필드를 동적으로 매핑
        return switch (sort) {
            case DUE_DATE_ASC, DUE_DATE_DESC -> "dueDate";
            case MEMBER_ASC, MEMBER_DESC -> "pmember";
            case CREATED_DATE_ASC, CREATED_DATE_DESC -> "createdDate";
            case PRIORITY_ASC, PRIORITY_DESC -> "priority";
            default -> "createdDate";  // 기본값 (추가적으로 예외 처리할 수 있음)
        };
    }
    // 추가: 제목으로만 검색하는 메서드
    public List<ResponseTaskDto> searchTasksByTitle(String searchWord) {
        return taskModelMapper.toResponseDtoList(taskRepository.findByTitleContaining(searchWord));
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
                .dueDate(requestDto.getDueDate()).build();
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
    // JOINCREATE
    public ResponseTaskDto joinCreateTask(CreateTaskRequestDto requestDto, String taskTitle) {
        Project project = projectRepository.findByPid(requestDto.getPid());
        Member member = memberRepository.findByEmail(requestDto.getMid())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Task task = Task.builder()
                .pid(project)
                .mid(member)
                .pmember(requestDto.getPmember())
                .title(taskTitle)
                .content(requestDto.getContent())
                .priority(requestDto.getPriority())
                .taskStatus(requestDto.getTaskStatus())
                .build();
        Task createdTask = taskRepository.save(task);
        return taskModelMapper.toResponseDto(createdTask);
    }
}

