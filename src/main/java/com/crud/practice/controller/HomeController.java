package com.crud.practice.controller;

import com.crud.practice.board.entity.Board;
import com.crud.practice.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String home(){
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile multipartFile) throws Exception{
        boardService.write(board, multipartFile);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.findAllList());
        return "boardlist";
    }

    @GetMapping("/board/view")
    // localhost:9090/board/view?id=1
    // id=1이 파라미터로 들어감
    public String boardView(Model model, Long id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }


    @GetMapping("/board/delete")
    public String boardDelete(Long id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Long id, Model model){
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, Board board, Model model, MultipartFile multipartFile)throws Exception{
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, multipartFile);
        return "redirect:/board/list";
    }
}
