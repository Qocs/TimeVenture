package com.TimeVenture.task.hold;

import com.TimeVenture.task.BaseTask;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Hold_Tasks")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class HoldTask extends BaseTask {
}
