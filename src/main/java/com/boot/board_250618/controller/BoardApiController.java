package com.boot.board_250618.controller;

import com.boot.board_250618.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class BoardApiController {
    @Autowired
    private BoardRepository boardRepository;

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable Long id){
        log.info("@# deleteBoard");
//        ROLE_ADMIN 한해서 허용
        
        boardRepository.deleteById(id);
        //삭제 2버튼에 대한 컨트롤러 + jpa<- ajax 사용+rest 방식
    }


}
