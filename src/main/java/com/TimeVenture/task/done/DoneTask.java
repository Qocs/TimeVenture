package com.TimeVenture.task.done;

import com.TimeVenture.task.BaseTask;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Done_Tasks")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DoneTask extends BaseTask {
}
