package com.boot.board_250618.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@Slf4j
public class AccountController {
    @RequestMapping("/login")
    public String login(){
        log.info("@# login()");

        return "account/login";
    }
}
