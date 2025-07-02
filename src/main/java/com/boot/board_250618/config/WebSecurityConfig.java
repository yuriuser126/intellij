package com.boot.board_250618.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration //설정파일
@EnableWebSecurity //시큐리티관해
public class WebSecurityConfig {
    @Autowired
    private DataSource dataSource;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/css/**", "/images/**","/account/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/account/login")
                        .loginProcessingUrl("/account/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/account/login?error")
                        .permitAll()

                )
                .logout((logout) -> logout.permitAll());

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

