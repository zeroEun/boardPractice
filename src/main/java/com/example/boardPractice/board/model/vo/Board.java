package com.example.boardPractice.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private Date createDate;
    private String status;

}
