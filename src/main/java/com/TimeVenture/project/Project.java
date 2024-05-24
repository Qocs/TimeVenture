package com.TimeVenture.project;

import com.TimeVenture.chat.Chat;
import com.TimeVenture.projectMember.ProjectMember;
import com.TimeVenture.task.doing.DoingTask;
import com.TimeVenture.task.done.DoneTask;
import com.TimeVenture.task.hold.HoldTask;
import com.TimeVenture.task.toDo.TodoTask;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Project {

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TodoTask> toDoTasks;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DoingTask> doingTasks;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HoldTask> holdTasks;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DoneTask> endTasks;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Chat> chats;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectMember> projectMembers;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(nullable = false)
    private String pName;

    @Column
    private String pExplain;

    @Column
    private Date pStartDate;

    @Column
    private Date pCreateDate;

    @Column
    private Date pUpdateDate;

    @Column
    private Date pEndDate;

    @Column(nullable = false)
    private String pColor;

    public Project(ProjectRequestDto projectRequestDto) {
        this.pExplain = projectRequestDto.getPExplain();
        this.pCreateDate = projectRequestDto.getPCreateDate();
        this.pStartDate = projectRequestDto.getPStartDate();
        this.pUpdateDate = projectRequestDto.getPUpdateDate();
        this.pEndDate = projectRequestDto.getPEndDate();
        this.pColor = projectRequestDto.getPColor();
    }

    public Project(String pExplain, Date pCreateDate, Date pStartDate, Date pUpdateDate, Date pEndDate, String pColor) {
        this.pExplain = pExplain;
        this.pCreateDate = pCreateDate;
        this.pStartDate = pStartDate;
        this.pUpdateDate = pUpdateDate;
        this.pEndDate = pEndDate;
        this.pColor = pColor;
    }

    public void update(ProjectRequestDto projectRequestDto) {
        this.pExplain = projectRequestDto.getPExplain();
        this.pCreateDate = projectRequestDto.getPCreateDate();
        this.pStartDate = projectRequestDto.getPStartDate();
        this.pUpdateDate = projectRequestDto.getPUpdateDate();
        this.pEndDate = projectRequestDto.getPEndDate();
        this.pColor = projectRequestDto.getPColor();
    }
}
