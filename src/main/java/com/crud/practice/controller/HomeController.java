package com.crud.practice.controller;

import com.crud.practice.board.entity.Board;
import com.crud.practice.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String home(){
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro( Board board){
        boardService.write(board);

        return "boardwrite";
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
}
