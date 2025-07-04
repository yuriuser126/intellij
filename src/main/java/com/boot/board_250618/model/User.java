package com.boot.board_250618.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;


    @ManyToMany
    @JoinTable(
            name = "user_role", //테이블 이름
            joinColumns = @JoinColumn(name = "user_id"), //user쪽
            inverseJoinColumns = @JoinColumn(name = "role_id")) //role쪽
//    Set<Course> likedCourses;

//    private List<Role> roles;
// 권한들을 가져올수있게 사용자의 여러 권한 -> 새로운 객체로
    private List<Role> roles=new ArrayList<>();


}


