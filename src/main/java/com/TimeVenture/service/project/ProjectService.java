package com.TimeVenture.service.project;

import com.TimeVenture.model.dto.project.AddProjectRequestDto;
import com.TimeVenture.model.dto.project.UpdateProjectDto;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.repository.project.ProjecetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjecetRepository projecetRepository;

    @Transactional
    public Project save(AddProjectRequestDto addProjectRequestDto) {
        return projecetRepository.save(addProjectRequestDto.toEntity());
    }

    public List<Project> findAll() {
        return projecetRepository.findAll();
    }

    public Project findById(int id) {
        return projecetRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(int id) {
        projecetRepository.deleteById(id);
    }

    @Transactional
    public Project update(int id, UpdateProjectDto request) {
        Project project = projecetRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        project.update(request.getPName(),
                request.getPExplain(),
                request.getPStartDate(),
                request.getPEndDate(),
                request.getPColor());

        return project;
    }
}
