package com.example.boardPractice.security.model.vo;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail implements UserDetails {

    //DB에서 읽어들인 정보는 UserDetail 인터페이스를 구현하고 있는 객체에 저장되어야 함

    private String name;
    private String username; //userId
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

}
