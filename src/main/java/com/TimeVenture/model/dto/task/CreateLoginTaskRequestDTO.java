package com.TimeVenture.model.dto.task;

import com.TimeVenture.model.entity.task.Task;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CreateLoginTaskRequestDTO {

    private int tid;
    private String mid;
    private String title;
    private int pid;
    private Timestamp createdDate;

    public Task toEntity() {

        // regDate가 null이면 현재 시간으로 설정
        Timestamp registrationDate = (createdDate != null) ? createdDate : new Timestamp(System.currentTimeMillis());

        return Task.builder()
                .tid(tid)
                .mid(mid)
                .title(title)
                .pid(pid)
                .createdDate(registrationDate)
                .build();
    }
}
