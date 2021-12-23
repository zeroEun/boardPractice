package com.example.boardPractice.security.model.service;

import com.example.boardPractice.security.model.vo.User;
import com.example.boardPractice.security.model.vo.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDbService {

    //로그인한 사용자 id를 파라미터로 받아들여 User, List<UserRole> 객체를 리턴하는 메소드 포함
    public User getUser(String userId);
    public List<UserRole> getUserRoles(String userId);

}
