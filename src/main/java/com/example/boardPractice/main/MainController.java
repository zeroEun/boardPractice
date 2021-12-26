package com.example.boardPractice.main;

import com.example.boardPractice.security.model.vo.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(){

        return "main";
    }

    @ResponseBody
    @GetMapping(value = "/selectUserId", produces = "text/html; charset=utf-8")
    public String selectUserId(){

        //로그인 하지 않은 사용자의 SecurityContextHolder.getContext().getAuthentication().getPrincipal() return값은 자료형이 String value가 anonymousUser이다.

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = "";
        if (principal instanceof UserDetail) {
            userId = ((UserDetail)principal).getName();
        } else {
            userId = principal.toString();
        }

        /*
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetail);

        String userId = "";
        if(userDetail != null){
            userId = userDetail.getName();
        }else {
            userId = null;
        }*/

        System.out.println("usrID" + userId);
        return userId;
    }

}
