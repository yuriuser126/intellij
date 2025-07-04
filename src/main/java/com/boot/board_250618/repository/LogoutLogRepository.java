package com.boot.board_250618.repository;

import com.boot.board_250618.model.LogoutLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogoutLogRepository extends JpaRepository<LogoutLog, Long> {
    // 네이티브 쿼리로 날짜별 로그아웃 수 조회 (MariaDB DATE 함수 사용)
    @Query(value = """
        SELECT DATE(logout_time) AS logoutDate, COUNT(*) AS logoutCount
        FROM logout_log
        GROUP BY logoutDate
        ORDER BY logoutDate ASC
    """, nativeQuery = true)
    List<Object[]> findDailyLogoutCountsRaw();
//    우선 Object[] 리스트로 받습니다
    //이건 시간별
    @Query(value = """
        SELECT DATE_FORMAT(logout_time, '%Y-%m-%d %H') AS logoutHour,
               COUNT(*) AS logoutCount
        FROM logout_log
        GROUP BY logoutHour
        ORDER BY logoutHour ASC
    """, nativeQuery = true)
    List<Object[]> findHourlyLogoutCountsRaw();

}
