package com.TimeVenture.controller.task;

import com.TimeVenture.model.TaskModelMapper;
import com.TimeVenture.model.dto.task.CreateTaskRequestDto;
import com.TimeVenture.model.dto.task.ResponseTaskDto;
import com.TimeVenture.model.dto.task.UpdateTaskRequestDto;
import com.TimeVenture.model.entity.project.Project;
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

//    @GetMapping
//    public ResponseEntity<List<ResponseTaskDto>> getAllTasks() {
//        List<ResponseTaskDto> responseDtos = taskService.getAllTasks();
//        return ResponseEntity.ok().body(responseDtos);
//    }

    @GetMapping("/project/{pid}")
    public ResponseEntity<List<ResponseTaskDto>> getTasksByProjectId(@PathVariable("pid") Project pid) {
        List<ResponseTaskDto> responseDtos = taskService.getTasksByPid(pid);
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTaskDto> getTaskById(@PathVariable int id) {
        ResponseTaskDto responseDto = taskService.getTaskById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    public ResponseEntity<ResponseTaskDto> createTask(@RequestBody CreateTaskRequestDto requestDto) {
        ResponseTaskDto responseDto = taskService.createTask(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTaskDto> updateTask(@PathVariable int id, @RequestBody UpdateTaskRequestDto requestDto) {
        ResponseTaskDto responseDto = taskService.updateTask(id, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}