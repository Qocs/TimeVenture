package com.TimeVenture.controller;

import com.TimeVenture.model.dto.project.ResponseProjectDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class FrontController {

    @GetMapping("/main")
    public String getMainPage() {
        return "common/main"; // HTML 파일 이름 (확장자 제외)
    }
    @GetMapping("/project")
    public String getProjectPage(@ModelAttribute ResponseProjectDto projectDto, Model model) {
        model.addAttribute("project", projectDto);
        return "common/project";
    }
    @GetMapping("/project/calendar")
    public String getCalendarPage() {
        return "projectCalendar/calendar";
    }
    @GetMapping("/project/chat")
    public String getChattingPage() {
        return "projectChatting/chatting";
    }
    @GetMapping("/project/dash")
    public String getDashBoardPage() {
        return "projectDashBoard/dashBoard";
    }
    @GetMapping("/project/attachment")
    public String getAttachmentsPage() {
        return "projectFile/attachment";
    }
    @GetMapping("/home")
    public String getHomePage() {
        return "projectHome/home";
    }
    @GetMapping("/project/kanban")
    public String getKanbanBoardPage() {
        return "projectKanbanBoard/kanbanBoardList"; // HTML 파일 이름 (확장자 제외)
    }
    @GetMapping("/project/list")
    public String getListPage() {
        return "projectList/projectList"; // HTML 파일 이름 (확장자 제외)
    }
    @GetMapping("/outline")
    public String getOutlinePage() {
        return "projectStart/outline";
    }

}