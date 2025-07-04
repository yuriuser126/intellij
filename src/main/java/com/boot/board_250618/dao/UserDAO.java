package com.boot.board_250618.dao;

import com.boot.board_250618.model.User;
import org.apache.ibatis.annotations.Mapper;

import com.boot.board_250618.dto.UserRole; // 주의: dto 경로 확인

import java.util.ArrayList;
import java.util.Optional;

@Mapper
public interface UserDAO {

    // id가 PK라면 Optional<User>가 적합
//    public Optional<User> findById(long id);
//     ArrayList<User> findById(long id);
     ArrayList<UserRole> findById(long id);
}
