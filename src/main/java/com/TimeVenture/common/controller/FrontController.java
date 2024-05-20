package com.TimeVenture.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping("/projectKanbanBoard/kanbanBoardList")
    public String kanbanBoardList() {
        return "projectKanbanBoard/kanbanBoardList";
    }

    @GetMapping("/projectChatting/chatting")
    public String chatting() { return "projectChatting/chatting"; }
}

