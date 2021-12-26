package com.example.boardPractice.security.config;

import com.example.boardPractice.security.model.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //https://mangkyu.tistory.com/77
    //https://www.bottlehs.com/springboot/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-spring-security%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9D%B8%EC%A6%9D-%EB%B0%8F-%EA%B6%8C%ED%95%9C%EB%B6%80%EC%97%AC/
    //https://gregor77.github.io/2021/05/18/spring-security-03/

    //https://shinsunyoung.tistory.com/78

    //https://velog.io/@oyeon/Spring-Security%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8
    //https://github.com/junu0516/demo

    //security 를 설정하고 구현하는 클래스, HttpSecurity라는 세부적인 보안기능을 설정할 수 있는 API를 제공하는 클래스 생성

    @Autowired
    UserDetailService userDetailService;

    //정적자원에 대해서는 security 적용 제외
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()//authorizeHttpRequests() 작성 시 오류 //nested exception is java.lang.IllegalStateException: permitAll only works with HttpSecurity.authorizeRequests()
                .antMatchers("/", "/loginForm", "/selectUserId").permitAll() //접근 허용
                .antMatchers("/listView.bo").hasAuthority("ROLE_USER")
                .anyRequest().authenticated()
            .and()
                .formLogin()//로그인페이지
                .loginPage("/loginForm")
                .usernameParameter("userId")
                .passwordParameter("password")
                .loginProcessingUrl("/")
                .failureForwardUrl("/member/loginerror?login_error=1")
                .defaultSuccessUrl("/",true)
                .permitAll()
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        //authorizeHttpRequests() : 시큐리티 처리 시 HttpServletRequest 사용
        //anyRequest().authenticated() : 클라이언트 모든 요청은 사용자 인증이 되어야 가능
        //페이지에 인증하지 않고 접근할 수 있도록 하려면 에외처리 필요 : authorizeHttpRequests().anyRequest() 사이에 antMatchers("url주소").permitAll() 추가
    }

    //사용자 아이디와 패스워드를 인증을 담당할 AuthenticationManager 인터페이스에 인증용 객체를(UsernamePasswordAuthenticationToken) 만들어 위임

    //AuthenticationManager : 인증요청을 받고 Authentication을 채워준다.
    //유저의 요청을 AuthenticationFilter에서 Authentication 객체로 변환해 AuthenticationManager(ProviderManager)에게 넘겨주고,
    // AuthenticationProvider(DaoAuthenticationProvider)가 실제 인증을 한 이후에 인증이 완료되면 Authentication객체를 반환

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
