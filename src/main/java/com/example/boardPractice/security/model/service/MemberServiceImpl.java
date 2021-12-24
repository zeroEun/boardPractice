package com.example.boardPractice.security.model.service;

import com.example.boardPractice.security.model.dao.MemberDao;
import com.example.boardPractice.security.model.vo.User;
import com.example.boardPractice.security.model.vo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public User getUser(String userId) {

        User user = memberDao.getMemberById(userId);

        return new User(user.getName(), user.getUserId(), user.getPassword());
    }

    @Override
    public List<UserRole> getUserRoles(String userId) {
        List<UserRole> userRoles = memberDao.getRolesById(userId);

        List<UserRole> list = new ArrayList<>();

        for(UserRole role : userRoles){
            list.add(new UserRole(role.getUserId(), role.getRoleName()));
        }
        return list;
    }
}
