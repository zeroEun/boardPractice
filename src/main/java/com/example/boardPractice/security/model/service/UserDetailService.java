package com.example.boardPractice.security.model.service;

import com.example.boardPractice.security.model.vo.User;
import com.example.boardPractice.security.model.vo.UserDetail;
import com.example.boardPractice.security.model.vo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    //userDbService는 인터페이스로 해당 인터페이스를 구현하고 있는 객체가 Bean으로 등록되어 있어야 한다.
    @Autowired
    UserDbService userDbService;

    //사용자가 로그인할 때 아이디 입력 시 loadUserByUsername에 인자로 전달
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        //userId에 해당하는 정보를 데이터베이스에서 읽어 user객체에 저장
        User user = userDbService.getUser(userId);

        if(user == null){
            throw new UsernameNotFoundException("사용자 정보를 찾지 못했습니다.");
        }

        //유저 정보가 있을 경우에는 UserDetail인터페이스를 구현한 객체 리턴
        UserDetail userDetail = new UserDetail();
        userDetail.setUsername(user.getUserId());
        userDetail.setName(user.getName());
        userDetail.setPassword(user.getPassword());

        List<UserRole> userRoles = userDbService.getUserRoles(userId);

        //로그인한 사용자의 권한 정보를 GrantedAuthority를 구현하고 있는 SimpleGrantedAuthority객체에 리스트 추가, MemberRole 이름은 "ROLE_"로 시작되야 한다.
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(userRoles != null){
            for(UserRole userRole : userRoles){
                authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
            }
        }


        //userDetail객체에 권한 목록 설정
        userDetail.setAuthorities(authorities);
        userDetail.setEnabled(true);
        userDetail.setAccountNonExpired(true);
        userDetail.setAccountNonLocked(true);
        userDetail.setCredentialsNonExpired(true);

        return userDetail;
    }
}
