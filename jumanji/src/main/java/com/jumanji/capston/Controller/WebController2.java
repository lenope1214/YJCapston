package com.jumanji.capston.controller;

import com.jumanji.capston.data.Board;
import com.jumanji.capston.dto.BoardDto;
import com.jumanji.capston.service.TempService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class WebController2 {
    @Autowired
    TempService tempService;

    @GetMapping(value = {"", "/", "/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/lsb")
    public String test(Model model){ // html에다가 객체, 변수를 전달해주는 역할
        List<Board> boardList;
        boardList = tempService.getAll();
        model.addAttribute("boardList", boardList);
        return "lsb"; // html파일 매칭
    }

    @PostMapping("/board")
    public String postBoard(BoardDto dto){
        tempService.insert(dto);
        return "redirect:/lsb"; // dto  전송 혹은 리다이렉트
    }

    @PostMapping("/updateBoard")
    public String updateBoard(@RequestBody BoardDto dto){
        System.out.println(dto.toString());
        tempService.update(dto);
        return "/lsb"; // 홈페이지 전송 혹은 리다이렉트
    }
    
    @PostMapping("/deleteBoard")
    public String deleteBoard(@RequestBody BoardDto dto){
        tempService.delete(dto);
        return "/lsb"; 
    }
}
