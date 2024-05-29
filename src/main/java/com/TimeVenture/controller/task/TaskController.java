package com.TimeVenture.controller.task;

import com.TimeVenture.model.TaskModelMapper;
import com.TimeVenture.model.dto.task.CreateTaskRequestDto;
import com.TimeVenture.model.dto.task.ResponseTaskDto;
import com.TimeVenture.model.dto.task.UpdateTaskRequestDto;
import com.TimeVenture.model.entity.task.Task;
import com.TimeVenture.model.enums.TaskStatus;
import com.TimeVenture.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskModelMapper taskMapper;

    @GetMapping
    public ResponseEntity<List<ResponseTaskDto>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        List<ResponseTaskDto> responseDtos = taskMapper.toResponseDtoList(tasks);
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping("/{pid}")
    public ResponseEntity<List<ResponseTaskDto>> getTasksByProjectId(@PathVariable int pid, @RequestParam(required = false) TaskStatus status) {
        List<Task> tasks = taskService.getTasksByPid(pid, status, null);
        List<ResponseTaskDto> responseDtos = taskMapper.toResponseDtoList(tasks);
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTaskDto> getTaskById(@PathVariable int id) {
        Task task = taskService.getTaskById(id);
        ResponseTaskDto responseDto = taskMapper.toResponseDto(task);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    public ResponseEntity<ResponseTaskDto> createTask(@RequestBody CreateTaskRequestDto requestDto) {
        Task task = taskMapper.toEntity(requestDto);
        Task createdTask = taskService.createTask(task);
        ResponseTaskDto responseDto = taskMapper.toResponseDto(createdTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTaskDto> updateTask(@PathVariable int id, @RequestBody UpdateTaskRequestDto requestDto) {
        Task task = taskMapper.toEntity(requestDto);
        Task updatedTask = taskService.updateTask(id, task);
        ResponseTaskDto responseDto = taskMapper.toResponseDto(updatedTask);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}