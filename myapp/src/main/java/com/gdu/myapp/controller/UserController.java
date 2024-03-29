package com.gdu.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.myapp.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {

  private final UserService userService;
 
  // @Autowired 생략
  public UserController(UserService userService) {
    super();
    this.userService = userService;
  }
  
  @GetMapping(value="/signin.page")
  public String signinPage(HttpServletRequest request
                          , Model model) {
  // 로그인 할 당시 있었던 화면으로 로그인 후에 돌려보내줘야 한다. 이전페이지 주소를 알아내 보내준다 -> request의 header 값에 이전주소값이 들어있다.
    //Sign In 페이지 이전의 주소가 저장되어 있는 Request Header 의 referer
    String referer = request.getHeader("referer");
    
    // referer 로 돌아가면 안 되는 예외 상황 (아이디 찾기 화면, 비밀번호 찾기 화면, 가입화면... 사용자가 원하는 '이전페이지'가 아님)
    String[] excludeUrls = {}; // 그런 예외사항 주소들 하나씩 배열에 집어넣어 준다.
    
    // Sign In 이후 이동할 url
    String url = referer;
    if(referer != null) {                       // 사이트 오자마자 로그인 = referer이 null 값임 -> 그럼 로그인하면 메인페이지로 가게 하기
      for(String excludeUrl : excludeUrls) {
        if(referer.contains(excludeUrl)) {  //excludeUrls 들은 메인페이지로 보낸다.
          url =  request.getContextPath() +  "/main.page";
          break;
        }
      }
    } else {
      url = request.getContextPath() + "/main.page";         // referer이 없으면 메인페이지로 보낸다.
    }
    
    // Sign In 페이지로 url 넘겨 주기
    model.addAttribute("url", url);
    
    return "user/signin";
  }
  
  @PostMapping("/signin.do")
  public void signin(HttpServletRequest request, HttpServletResponse response) {
    userService.signin(request, response);
    
    
  }
  
  
  
}
