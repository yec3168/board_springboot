package com.crud.practice.board.service;

import com.crud.practice.board.entity.Board;
import com.crud.practice.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardService {

    @Autowired //스프링빈이 알아서 인스턴스 생성
    private BoardRepository boardRepository;

    //글 작성
    public void write(Board board){
        boardRepository.save(board); //레포지토리에 저장
    }

    //목록 리스트 불러오기
    public List<Board> findAllList(){
       return boardRepository.findAll();
    }

    // 특정 목록 게시물 불러오기
    public Board boardView(Long id){
        return boardRepository.findById(id).get();
    }
}
