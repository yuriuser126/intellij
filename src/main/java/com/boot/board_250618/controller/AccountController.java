package com.boot.board_250618.controller;

import com.boot.board_250618.model.User;
import com.boot.board_250618.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(){
        log.info("@# login()");

        return "account/login";
    }

    //register.html로 향해감
    @GetMapping("/register")
    public String register(){
        log.info("@# GetMapping register()");


        return "account/register";
    }

    //register.html 이후 회원가입후 저장후 홈화면 등록을 시키는거
    @PostMapping("/register")
    public String register(User user){
        log.info("@#  PostMapping register()");
        log.info("@# register user=>()"+user);

        userService.save(user);

        return "redirect:/";
    }
}
