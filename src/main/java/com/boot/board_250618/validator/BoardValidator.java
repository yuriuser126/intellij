package com.boot.board_250618.validator;

import com.boot.board_250618.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@Slf4j
public class BoardValidator implements Validator {

    public boolean supports(Class clazz) {return Board.class.equals(clazz);}


    @Override
    public void validate(Object target, Errors errors) {
        log.info("@# validate()");

        //다운캐스팅 b->게시글의 정보
        Board b = (Board)target;
        log.info("@# b->"+b);

        //게시글 저장할때 사용할거다 ,  에러코드 키로 받음
        if (b.getContent().equals("")){
            errors.rejectValue("content","key", "내용을입력하세요");

        }


    }
}