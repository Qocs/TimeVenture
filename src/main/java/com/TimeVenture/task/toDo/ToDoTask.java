package com.TimeVenture.task.todo;

import com.TimeVenture.task.BaseTask;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@Table(name = "Todo_Tasks")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TodoTask extends BaseTask {
}
