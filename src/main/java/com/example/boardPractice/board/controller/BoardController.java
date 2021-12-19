package com.example.boardPractice.board.controller;

import com.example.boardPractice.board.model.service.BoardService;
import com.example.boardPractice.board.model.vo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String selectBoardList(Model model){

        ArrayList<Board> list = boardService.selectBoardList();
        model.addAttribute("list", list);

        return "board/boardListView";
    }



}
