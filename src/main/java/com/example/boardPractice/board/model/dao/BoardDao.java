package com.example.boardPractice.board.model.dao;

import com.example.boardPractice.board.model.vo.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BoardDao {

    ArrayList<Board> selectBoardList();
}
