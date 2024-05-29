package com.TimeVenture.repository.task;

import com.TimeVenture.model.entity.task.Task;
import com.TimeVenture.task.model.enums.TaskStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
    // #{#entityName} : Spring Data JPA 에서 제공하는 SpEL(Spring Expression Language)의 표현식. T로 대체된다.: Spring Data JPA 에서 제공하는 SpEL(Spring Expression Language)의 표현식. T로 대체된다.
    @Query("SELECT t FROM Task t WHERE t.pid = :id")
    List<Task> findAllByPid(@Param("id") int id);

    List<Task> findByPidAndTaskStatus(int pid, TaskStatus taskStatus, Sort sort);
    List<Task> findByPidAndTaskStatusAndPmemberAndTitleContaining(int pid, TaskStatus taskStatus, int pmember,
                                                               String searchWord, Sort sort);

}
