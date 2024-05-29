package com.TimeVenture.model.dto.project;

import com.TimeVenture.model.entity.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseProjectDto {

    private Long pid;
    private String pExplain;
    private String pName;
    private Date pStartDate;
    private Date pEndDate;
    private String pColor;

    public ResponseProjectDto(Project project) {
        this.pid = project.getPid();
        this.pExplain = project.getPexplain();
        this.pName = project.getPname();
        this.pStartDate = project.getPstartDate();
        this.pEndDate = project.getPendDate();
        this.pColor = project.getPcolor();
    }
}
