package com.boot.board_250618.config;

import com.boot.board_250618.Handler.CustomLogoutSuccessHandler;
import com.boot.board_250618.controller.CustomAuthorizationRequestResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration //설정파일
@EnableWebSecurity //시큐리티관해
public class WebSecurityConfig {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

//    소셜로그인 사용자 db저장
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable);
//        HTTP 요청에서 CSRF 토큰 검사를 하지 않겠다
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/css/**", "/images/**","/account/register","/api/**","/logout/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // 관리자 권한 필요
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/account/login")
                        .loginProcessingUrl("/account/login") // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/")
                        .failureUrl("/account/login?error")
                        .permitAll()

                )
//                .oauth2Login(Customizer.withDefaults())
                .oauth2Login(oauth2Login->
                        oauth2Login
                                .loginPage("/account/login") //로그인페이지로 호출
                                .authorizationEndpoint(authorization ->
                                 authorization
                                .authorizationRequestResolver(
                                new CustomAuthorizationRequestResolver(clientRegistrationRepository)
                                ))
                                .defaultSuccessUrl("/",true) // 로그인 성공 후 리디렉션
//                                .defaultSuccessUrl("/user-info",true) // 로그인 성공 후 리디렉션
                )
                .logout(logout -> logout
                        .logoutSuccessHandler(customLogoutSuccessHandler) // 커스텀 핸들러 등록
                        .invalidateHttpSession(true)  // 세션 무효화
                        .deleteCookies("JSESSIONID")  // 쿠키 삭제
                        .permitAll()
                );
        return http.build();
    }

    @Bean //패스워드암호화
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                //패스워드 암호화 _ 메소드를 스프링에서 관리
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, PASSWORD, enabled " +
                                        " FROM USER " +
                                        "WHERE username =?")
                .authoritiesByUsernameQuery("SELECT u.username, r.name " +
                                            " FROM user_role ur " +
                                            "Inner JOIN USER u " +
                                            "  ON ur.user_id = u.id " +
                                            "INNER JOIN ROLE r " +
                                            "  ON ur.role_id = r.id " +
                                            "WHERE u.username =?");
    }

}

