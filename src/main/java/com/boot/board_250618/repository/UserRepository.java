package com.boot.board_250618.repository;

import com.boot.board_250618.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

//유저 이름 찾아올거임 -> 서비스
//USer쪽 id pk long타입
