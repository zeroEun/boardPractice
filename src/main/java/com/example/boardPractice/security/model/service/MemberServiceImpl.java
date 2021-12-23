package com.example.boardPractice.security.model.service;

import com.example.boardPractice.security.model.vo.User;
import com.example.boardPractice.security.model.vo.UserRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public User getUser(String userId) {
        return new User("carami", "$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m");
    }

    @Override
    public List<UserRole> getUserRoles(String userId) {
        List<UserRole> list = new ArrayList<>();
        list.add(new UserRole("carami", "ROLE_USER"));
        return list;
    }
}
