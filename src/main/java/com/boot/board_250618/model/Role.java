package com.boot.board_250618.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    User의 private List<Role> roles
    @ManyToMany(mappedBy = "roles")

    private List<User> users; //->사용자객체

//    Set<Student> likes; -> 권한에 해당되는 사용자들이 오면됨
}
