package com.example.boardPractice.NXPractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NXPracticeController {

    @GetMapping("/NXView")
    public String NXView(){

        return "NXPractice/nxView";
    }
}
