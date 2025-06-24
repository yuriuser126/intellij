package com.boot.board_250618.validator;

import com.boot.board_250618.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class BoardValidator implements Validator {
    public boolean supports(Class clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("@# validate()");

        Board b = (Board) target;
        log.info("@# b=>"+b);

        if (b.getContent().equals("")){
            errors.rejectValue("content","key","내용을 입력하세요.");
        }
    }
}