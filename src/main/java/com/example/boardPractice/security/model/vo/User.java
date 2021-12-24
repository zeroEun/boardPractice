package com.example.boardPractice.security.model.vo;

import lombok.*;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String name;
    private String password;

}
