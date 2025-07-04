package com.boot.board_250618.controller;

import com.boot.board_250618.dto.LogoutCount;

import com.boot.board_250618.service.LogoutLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class LogoutLogApiController {

    @Autowired
    private LogoutLogService logoutLogService;

    // API 응답용 메서드 / 원래 restcontroller사용중이였음 분기처리
    @GetMapping("/api/logout/daily-count")
    @ResponseBody 
    public List<LogoutCount> getDailyLogoutCounts() {
        return logoutLogService.getDailyLogoutCounts();
    }
    // 관리자 전용 차트 페이지 (뷰 반환용)
    @GetMapping("/admin/logout-logs/chart")
    public String showLogoutChartPage() {
        return "logout/logout-logs";  // Thymeleaf 뷰 이름 반환
    }

    @GetMapping("/api/logout/hourly-count")
    @ResponseBody
    public List<LogoutCount> getHourlyLogoutCounts() {
        return logoutLogService.getHourlyLogoutCounts();
    }



}
