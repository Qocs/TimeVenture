package com.TimeVenture.project;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class ProjectRequestDto {

    private String pExplain;

    private Date pCreateDate;

    private Date pStartDate;

    private Date pUpdateDate;

    private Date pEndDate;

    private String pColor;

    public ProjectRequestDto(String pExplain,Date pCreateDate, Date pStartDate, Date pUpdateDate, Date pEndDate, String pColor) {
        this.pExplain = pExplain;
        this.pCreateDate = pCreateDate;
        this.pStartDate = pStartDate;
        this.pUpdateDate = pUpdateDate;
        this.pEndDate = pEndDate;
        this.pColor = pColor;
    }
}
