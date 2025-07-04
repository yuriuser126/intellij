package com.boot.board_250618.controller;

import com.boot.board_250618.dto.UserRole;
import com.boot.board_250618.model.User;
import com.boot.board_250618.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/search")

//    public String search(){
    public String search(@RequestParam(required = false) String id, Model model){
//    public String search(Model model){
        log.info("@# GetMapping  search()");
        log.info("@# search id=>()"+id);

        if (id == null) {
            id="0"; //오토인크리먼트니까 0자체가 없음
        }
        log.info("@# search id 0 분기처리후=>()"+id);

//       ArrayList<UserRole> users = userService.findByUsingMapper(1L);
       ArrayList<UserRole> users = userService.findByUsingMapper(Long.parseLong(id));
//       ArrayList<User> users = userService.findByUsingMapper(1L);
//        Optional<User> users = userService.findByUsingMapper(1L);
        log.info("@#  users->"+users);

        model.addAttribute("users",users);
        //모델로 보내는거

        return "/account/user";
    }



}
