package com.boot.board_250618.service;

import com.boot.board_250618.model.Board;
import com.boot.board_250618.model.User;
import com.boot.board_250618.repository.BoardRepository;
import com.boot.board_250618.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BoardService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BoardRepository boardRepository;
    
    public Board save(String username, Board board){
        log.info("@# save()");
        log.info("@# save() username"+username);
        log.info("@# save() board"+board);
        
        User user = userRepository.findByUsername(username);
        board.setUser(user); //로그인된 유저 가지고와서 보드객ㅇ체에 새로 덮어쓰기
        
        return boardRepository.save(board);
    }
}
