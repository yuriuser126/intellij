package com.boot.board_250618.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogoutCount {

    private String logoutDate;  // 로그아웃 날짜
    private int logoutCount; // 해당 날짜 로그아웃 건수


}
