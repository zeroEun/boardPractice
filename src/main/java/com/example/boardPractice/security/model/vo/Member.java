package com.example.boardPractice.security.model.vo;


import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private int userNo;
    private String userName;
    private String userId;
    private String password;

}
