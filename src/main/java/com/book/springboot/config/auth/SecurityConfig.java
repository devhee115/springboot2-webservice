package com.book.springboot.config.auth;

import com.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // SpringSecurity 설정들을 활성화 시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable함
                .and()
                .authorizeRequests() //URL별 권한관리를 설정하는 옵션의 시작점, authorizeRequests가 선언되어야만 antMatchers옵션을 사용할 수 있음.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // antMatchers : 권한 관리 대상을 지정하는 옵션, URL, HTTP메소드별로 관리가 가능
                .anyRequest().authenticated() // anyRequest : 설정된 값들 이외 나머지 URL들 나타냄 여기서는 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 함(로그인한 사용자들)
                .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공시 /주소로 이동
                .and()
                .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당함
                .userService(customOAuth2UserService); // 소셜로그인 성공시 후속 조치를 진행할 인터페이스의 구현체 등록, 리소스서버(소셜서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능을 명시할 수 있음
    }
}
