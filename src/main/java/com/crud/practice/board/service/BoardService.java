package com.crud.practice.board.service;

import com.crud.practice.board.entity.Board;
import com.crud.practice.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;


@Service
public class BoardService {

    @Autowired //스프링빈이 알아서 인스턴스 생성
    private BoardRepository boardRepository;

    //글 작성
    public void write(Board board, MultipartFile multipartFile) throws Exception{
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files"; // project 경로를 담아줌

        UUID uuid = UUID.randomUUID();//식별자
        String fileName = uuid + "_" + multipartFile.getOriginalFilename();

        File saveFile = new File(projectPath, fileName); // 경로, 이름 해서 저장
        multipartFile.transferTo(saveFile);

        board.setFileName(fileName);
        board.setFilePath("/files/"+fileName);
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

    public void boardDelete(Long id){
        boardRepository.deleteById(id);
    }
}
