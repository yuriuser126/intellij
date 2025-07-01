package com.boot.board_250618.repository;

import com.boot.board_250618.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}

//	JPA가 다룰 엔티티 클래스 (DB 테이블과 매핑되는 자바 클래스)
//Long = Board 클래스의 기본 키(@Id)의 타입
/* 한 줄만 선언-> findAll()	전체 목록 조회
findById(Long id)	ID로 조회
save(Board board)	새 데이터 저장 or 수정
deleteById(Long id)	ID로 삭제 */