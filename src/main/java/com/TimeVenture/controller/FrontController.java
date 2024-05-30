package com.TimeVenture.controller;

import com.TimeVenture.service.project.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    private final ProjectService projectService;

    public FrontController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/main")
    public String getMainPage() {
        return "common/main"; // HTML 파일 이름 (확장자 제외)
    }
    @GetMapping("/project")
    public String getProjectPage() {
        return "common/project";
    }
    @GetMapping("/calendar")
    public String getCalendarPage() {
        return "projectCalendar/calendar";
    }
    @GetMapping("/chat")
    public String getChattingPage() {
        return "projectChatting/chatting";
    }
    @GetMapping("/dash")
    public String getDashBoardPage() {
        return "projectDashBoard/dashBoard";
    }
    @GetMapping("/attachment")
    public String getAttachmentsPage() {
        return "projectFile/attachment";
    }
    @GetMapping("/home")
    public String getHomePage() {
        return "projectHome/home";
    }
    @GetMapping("/kanban")
    public String getKanbanBoardPage() {
        return "projectKanbanBoard/kanbanBoardList"; // HTML 파일 이름 (확장자 제외)
    }
    @GetMapping("/list")
    public String getListPage() {
        return "projectList/projectList"; // HTML 파일 이름 (확장자 제외)
    }
    @GetMapping("/outline")
    public String getOutlinePage() {
        return "projectStart/outline";
    }

}