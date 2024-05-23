package com.TimeVenture.task.doing;

import com.TimeVenture.task.BaseTask;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Doing_Tasks")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DoingTask extends BaseTask {
}
