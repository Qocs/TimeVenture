package com.TimeVenture.join.controller;

import com.TimeVenture.join.service.EmailService;
import com.TimeVenture.join.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JoinController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

//    @GetMapping("/join")
//    public String requestVerificationForm() {
//        return "member/join";
//    }

//    @PostMapping("/request-verification")
//    public String requestVerification(@RequestParam String email) {
//        String token = tokenService.generateToken(email);
//        emailService.sendVerificationEmail(email, token);
//        return "check-your-email";
//    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String email, @RequestParam String token, Model model) {
        if (tokenService.validateToken(email, token)) {
            // 토큰이 유효하면 회원가입 폼으로 이동
            model.addAttribute("email", email);
            return "signup-form";
        } else {
            // 토큰이 유효하지 않으면 에러 페이지로 리다이렉트
            return "redirect:/error";
        }
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
}
