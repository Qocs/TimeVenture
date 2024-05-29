package com.TimeVenture.controller.project;

import com.TimeVenture.model.dto.project.AddProjectRequestDto;
import com.TimeVenture.model.dto.project.ResponseProjectDto;
import com.TimeVenture.model.dto.project.UpdateProjectDto;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity<Project> addProject(@RequestBody AddProjectRequestDto requestDto) {
        Project savedProject = projectService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ResponseProjectDto>> findAllProjects() {
        List<ResponseProjectDto> projects = projectService.findAll().stream().map(ResponseProjectDto::new).toList();
        return ResponseEntity.ok().body(projects);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ResponseProjectDto> findOneProject(@PathVariable Long id) {
        Project project = projectService.findById(id);

        return ResponseEntity.ok().body(new ResponseProjectDto(project));
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<ResponseProjectDto> deleteProject(@PathVariable Long id) {
        projectService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody UpdateProjectDto request) {
        Project updatedProject = projectService.update(id, request);

        return ResponseEntity.ok().body(updatedProject);
    }
}
