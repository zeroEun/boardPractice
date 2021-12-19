package com.example.boardPractice.board.model.service;

import com.example.boardPractice.board.model.dao.BoardDao;
import com.example.boardPractice.board.model.vo.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDao boardDao;

    @Override
    public ArrayList<Board> selectBoardList() {

        return boardDao.selectBoardList();
    }
}
