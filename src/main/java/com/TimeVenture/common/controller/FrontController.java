package com.TimeVenture.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

<<<<<<< HEAD
    @GetMapping("/project/kanbanBoardList")
=======
    @GetMapping("/project/kanban")
>>>>>>> a22ea2e327f0acfd10ce43422216db392876dbbf
    public String kanbanBoardList() {
        return "projectKanbanBoard/kanbanBoardList";
    }

    @GetMapping("/main")
    public String Main() {
        return "common/main";
    }

    @GetMapping("/project/attachments")
    public String Attachments() {
        return "projectFile/attachment";
    }

<<<<<<< HEAD
=======
    @GetMapping("/project/list")
    public String ProjectList() {
        return "projectList/projectList";
    }

    @GetMapping("/home")
    public String Home() {
        return "projectHome/home";
    }

    @GetMapping("/project")
    public String Project() {
        return "common/project";
    }

    @GetMapping("/project/start")
    public String ProjectStart() {
        return "projectStart/outline";
    }

    @GetMapping("/project/home")
    public String ProjectHome() {
        return "projectHome/home";
    }

    @GetMapping("/project/dash")
    public String ProjectDash() {
        return "projectDashBoard/dashboard";
    }

    @GetMapping("/project/chatting")
    public String ProjectChatting() {
        return "projectChatting/chatting";
    }

    @GetMapping("/project/calendar")
    public String ProjectCalendar() {
        return "projectCalendar/caldenar";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "member/myPage";

    }
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

>>>>>>> a22ea2e327f0acfd10ce43422216db392876dbbf
}

