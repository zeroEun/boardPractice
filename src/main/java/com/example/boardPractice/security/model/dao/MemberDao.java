package com.example.boardPractice.security.model.dao;

import com.example.boardPractice.security.model.vo.User;
import com.example.boardPractice.security.model.vo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberDao {

    public User getMemberById(String userId);
    public List<UserRole> getRolesById(String userId) ;
}
