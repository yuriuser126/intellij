package com.boot.board_250618.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRole {
//    세개를 조인하기때문에 전부가져옴
//user table
    private long id;
    private String username;
    private String password;
    private Boolean enabled;

//role table
    private String name;

//user_role table

    private long user_id;
    private long role_id;


}




