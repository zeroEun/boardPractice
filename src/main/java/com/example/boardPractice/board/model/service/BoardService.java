package com.example.boardPractice.board.model.service;

import com.example.boardPractice.board.model.vo.Board;

import java.util.ArrayList;

public interface BoardService {

    ArrayList<Board> selectBoardList();

    void insertBoard(Board board);

    Board selectBoard(int boardNo);

    Board updateBoard(Board board);

    int deleteBoard(int boardNo);
}
