package com.example.boardPractice.board.model.dao;

import com.example.boardPractice.board.model.vo.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BoardDao {

    //mapper id = 메서드명
    ArrayList<Board> selectBoardList();

    void insertBoard(Board board);

    Board selectBoard(int boardNo);

    void updateBoard(Board board);

    int deleteBoard(int boardNo);
}
