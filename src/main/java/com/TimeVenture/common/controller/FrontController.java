package com.TimeVenture.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping("/project/kanbanBoardList")
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

    @GetMapping("/project/chatting")
    public String Chatting(){
        return "projectChatting/chatting";
    }

}

