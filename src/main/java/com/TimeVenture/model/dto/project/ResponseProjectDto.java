package com.TimeVenture.model.dto.project;

import com.TimeVenture.model.entity.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
@AllArgsConstructor
@Getter
public class ResponseProjectDto {

    private String pExplain;
    private String pName;
    private Date pStartDate;
    private Date pEndDate;
    private String pColor;

    public ResponseProjectDto(Project project) {
        this.pExplain = project.getPexplain();
        this.pName = project.getPname();
        this.pStartDate = project.getPstartDate();
        this.pEndDate = project.getPendDate();
        this.pColor = project.getPcolor();
    }
}
