package com.gdu.prj04.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdu.prj04.dto.BoardDto;
import com.gdu.prj04.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/ajax2")
@RequiredArgsConstructor
@RestController  // 모든 메소드는 @ResponseBody annotation 을 가지게 된다. ResponseBody 가 default 로 사용되는 어노테이션! ResponseBody 를 일일이 적어줄 필요가 없음. ajax 전용 컨트롤러!
public class BoardController2 {

  private final BoardService boardService;
  
  
  @GetMapping(value="/list.do", produces=MediaType.APPLICATION_JSON_VALUE) //"application/json" 이거 대신 써주는거 ㅎ 스프링은 뭘 대신 많이 써준다...오타방지^^
  public List<BoardDto> list() {
    return boardService.getBoardList();
  }
  
  @GetMapping(value="/detail.do", produces=MediaType.APPLICATION_JSON_VALUE)
  public BoardDto detail(BoardDto board) {
    
   return boardService.getBoardByNo(board.getBoardNo());
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
}
