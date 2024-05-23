package com.TimeVenture.project;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {
    public final ProjectRepository projectRepository;

    @Transactional
    public int update(int pid, ProjectRequestDto projectRequestDto) {
        Project project1 = projectRepository.findById(pid).orElseThrow(
                () -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다.")
        );
        project1.update(projectRequestDto);
        return project1.getPid();
    }
}
