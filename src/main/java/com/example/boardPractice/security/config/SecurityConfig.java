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
                .authorizeRequests()
                .antMatchers("/", "/loginForm", "/selectUserId").permitAll() //접근 허용
                .antMatchers("/scrapingView.htx").hasAuthority("ROLE_ADMIN") //권한 적용
                .anyRequest().authenticated()
            .and()
                .formLogin()//로그인페이지
                .loginPage("/loginForm")
                .usernameParameter("userId")
                .passwordParameter("password")
                .loginProcessingUrl("/")
                .failureUrl("/loginError")
                .defaultSuccessUrl("/",true)
                .permitAll()
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
            .and()
                .exceptionHandling()
                .accessDeniedPage("/WEB-INF/views/common/403error.jsp");

        //페이지에 인증하지 않고 접근할 수 있도록 하려면 에외처리 필요 : authorizeHttpRequests().anyRequest() 사이에 antMatchers("url주소").permitAll() 추가
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
