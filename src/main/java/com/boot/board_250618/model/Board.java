package com.boot.board_250618.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
//엔티티로 테이블 사용
@Entity
@Data
public class Board {
    //pk설정
    //제너레이티드 오토인크리먼트
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    //    @Size(min=2, max=30)//크기가 2에서 30 사이여야 합니다
    @Size (min=2, max=30 ,message ="제목은 2자이상 50자 이하여야합니다.")
    private String title;
    private String content;
}
