package com.example.boardPractice.board.model.vo;

import lombok.*;

import java.sql.Date;

@ToString
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
