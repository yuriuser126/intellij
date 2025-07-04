package com.boot.board_250618;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@MapperScan("com.boot.board_250618.dao")  // 이 부분 꼭 추가하세요!
public class Board250618Application {

	public static void main(String[] args) {
		SpringApplication.run(Board250618Application.class, args);
	}

}
