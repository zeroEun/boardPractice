package com.example.boardPractice.security.controller;

import com.example.boardPractice.security.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    //config .loginPage("/loginForm")와 동일
    @GetMapping("/loginForm")
    public String loginForm(String userId, String password){

        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(){

        return "redirect:/";
    }

}
