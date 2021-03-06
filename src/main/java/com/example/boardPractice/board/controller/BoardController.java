package com.example.boardPractice.board.controller;

import com.example.boardPractice.board.model.service.BoardService;
import com.example.boardPractice.board.model.vo.Board;
import com.example.boardPractice.security.model.vo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @ResponseBody
    @RequestMapping(value = "/selectList.bo", produces = "application/json; charset=UTF-8")
    public ArrayList<Board> selectBoardList(){

        return boardService.selectBoardList();
    }

    @RequestMapping("/listView.bo")
    public String boardListview(){

        return "board/boardListView";
    }

    @RequestMapping("/insert.bo")
    public String insertBoard(Board board, Model model){

        boardService.insertBoard(board);
        model.addAttribute("msg", "게시글이 등록되었습니다.");

        return "board/boardListView";
    }

    @RequestMapping("/boardDetail.bo")
    public String selectBoard(int boardNo, Model model){

        Board board = boardService.selectBoard(boardNo);
        model.addAttribute("b", board);

        return "board/boardDetailView";
    }

    @RequestMapping("/update.bo")
    public String updateBoard(Board board, Model model){

        Board b = boardService.updateBoard(board);
        model.addAttribute("b", b);
        model.addAttribute("msg", "게시글이 수정되었습니다.");

        return "board/boardDetailView";

    }

    @ResponseBody
    @RequestMapping("/delete.bo")
    public String deleteBoard(@RequestParam int boardNo){

        return boardService.deleteBoard(boardNo);

    }

}
