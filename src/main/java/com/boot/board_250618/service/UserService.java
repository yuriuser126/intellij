package com.boot.board_250618.service;

import com.boot.board_250618.model.Role;
import com.boot.board_250618.model.User;
import com.boot.board_250618.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //암호화를 위해 별도작업
    @Autowired
    private PasswordEncoder passwordEncoder;

//    유저정보 저장 메서드
    public User save(User user){
//        사용자 패스워드 가져와서 암호화
        String encodedPassword =  passwordEncoder.encode(user.getPassword());
        //새로 저장 암호화된 패스워드를 비밀번호 저장
        user.setPassword(encodedPassword);
        //활성화된 사용자(퇴사한 직원관리)
        user.setEnabled(true);

        Role role = new Role();
//        알아서 오토인크리먼트 됨 1L 넣으면
        role.setId(1L);
        //위에서 만든 객체 role에다 추가 + getRoles 이거 List 말하는거임
        user.getRoles().add(role);

        return userRepository.save(user);
    }
}
