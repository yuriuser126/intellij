package com.boot.board_250618.service;

import com.boot.board_250618.dto.LogoutCount;

import com.boot.board_250618.repository.LogoutLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LogoutLogService {

    @Autowired
    private LogoutLogRepository logoutLogRepository;

    // ✅ 일별 로그아웃 카운트
    public List<LogoutCount> getDailyLogoutCounts() {
        List<Object[]> rawList = logoutLogRepository.findDailyLogoutCountsRaw();
        List<LogoutCount> dtoList = new ArrayList<>();

        for (Object[] row : rawList) {
            String dateStr = row[0].toString(); // java.sql.Date → String
            int count = ((Number) row[1]).intValue();
            dtoList.add(new LogoutCount(dateStr, count));
        }

        return dtoList;
    }

    // ✅ 시간별 로그아웃 카운트
    public List<LogoutCount> getHourlyLogoutCounts() {
        List<Object[]> rawList = logoutLogRepository.findHourlyLogoutCountsRaw();
        List<LogoutCount> dtoList = new ArrayList<>();

        for (Object[] row : rawList) {
            String hourStr = row[0].toString(); // 예: "2025-07-04 15"
            int count = ((Number) row[1]).intValue();
            dtoList.add(new LogoutCount(hourStr, count));
        }

        return dtoList;
    }



}
