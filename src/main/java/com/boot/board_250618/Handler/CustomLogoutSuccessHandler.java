package com.boot.board_250618.Handler;

import com.boot.board_250618.model.LogoutLog;
import com.boot.board_250618.repository.LogoutLogRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final LogoutLogRepository logoutLogRepository;

    public CustomLogoutSuccessHandler(LogoutLogRepository logoutLogRepository) {
        this.logoutLogRepository = logoutLogRepository;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        if(authentication != null) {
            String username = authentication.getName();

            LogoutLog log = new LogoutLog();
            log.setUsername(username);
            log.setLogoutTime(LocalDateTime.now());

            logoutLogRepository.save(log);

            System.out.println("User logged out and saved to DB: " + username);
        }

        // 로그아웃 후 리다이렉트
        response.sendRedirect("/");
    }
}
