package com.TimeVenture.common.controller;

import com.TimeVenture.join.service.EmailService;
import com.TimeVenture.join.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FrontController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/project/kanban")
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
    public String mypage() {return "member/myPage";}

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/emailLogin")
    public String loginEmail() {
        return "member/emailLogin";
    }

    @GetMapping("/join")
    public String join() { return "member/join";}

    @PostMapping("/confirmEmail")
    public String confirmEmail(@RequestParam("email") String email) {
        String token = tokenService.generateToken(email);
        emailService.sendVerificationEmail(email, token);
        return "member/confirmEmail";
    }

    @GetMapping("/loginAccount")
    public String loginAccount() { return "member/loginAccount";}

    @GetMapping("/loginAccountSetup")
    public String loginAccountSetup() { return "member/loginAccountSetup";}

    @GetMapping("/loginAccountRole")
    public String loginAccountRole() { return "member/loginAccountRole";}

    @GetMapping("/loginFirstProj")
    public String loginFirstProj() { return "member/loginFirstProj";}

    @GetMapping("/loginNewTask")
    public String loginNewTask() { return "member/loginNewTask";}

    @GetMapping("/loginSendEmail")
    public String loginSendEmail() { return "member/loginSendEmail";}

    @GetMapping("/loginFinish")
    public String loginFinish() { return "member/loginFinish";}

    @GetMapping("/forgotPassword")
    public String forgotPassword() { return "member/findPassword";}

    @GetMapping("/testdetail")
    public String testdetail() {return "common/detail";}
    }


