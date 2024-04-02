package com.gdu.myapp.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.gdu.myapp.dto.UserDto;
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
    String[] excludeUrls = {"/findId.page", "/findPw.page", "/signup.page"}; // 그런 예외사항 주소들 하나씩 배열에 집어넣어 준다.
    
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
    
    // 즉 기본적으로 url = referer 이지만 예외상황인 경우에만 url을 main으로 변경하겠다.
    
    // Sign In 페이지로 url 넘겨 주기
    model.addAttribute("url", url);
    
    
    /******************* 네이버 로그인 1*/
    String redirectUri = "http://localhost:8080" + request.getContextPath() + "/user/naver/getAccessToken.do";
    String state = new BigInteger(130, new SecureRandom()).toString();
    // state 이렇게 뽑으라고 개발자센터 메뉴얼에 나온다
    
    StringBuilder builder = new StringBuilder();
    builder.append("https://nid.naver.com/oauth2.0/authorize");
    builder.append("?response_type=code");
    builder.append("&client_id=XeShO8IjERM4jJY5KFwX");
    builder.append("&redirect_uri=" + redirectUri);
    // 네이버 로그인 2단계에서 어느 주소로 접속할건지(redirect_url) 그 주소를 알려달라. 그 주소가 맞으면 해주고 아니면 안해주겠다.
    // 이후 토큰으로도 검증함. 너가 준 토큰이랑 내가 발급한 토큰이랑 맞으면 너라고 인증해주겠다.
    // 네이버 로그인 Callback URL  <<< 사용하기로 한 주소를 등록해줌. 크게 두가지 토큰 받는거 프로필(고객이 동의한 개인정보) 받는거
    // 우리 이 주소로 너희한테 정보 요청할거다. 그 주소 등록. localhost:8080 이 부분은 나중에 배포할 때 싹 들어내고 바꿔야 함
    builder.append("&state=" + state);
    
    model.addAttribute("naverLoginUrl", builder.toString());
    // 이 model에 저장된 정보는 jsp로 포워딩 된다. signin 페이지에서 naverLoginUrl를 EL로 확인할 수 있어짐.
    
    
    return "user/signin"; 
  }
  
  @PostMapping("/signin.do")
  public void signin(HttpServletRequest request, HttpServletResponse response) {
    userService.signin(request, response);
  }
  
  @GetMapping("/signup.page")
    public String signupPage() {
    return "user/signup";
  }
  
  @PostMapping(value="/checkEmail.do", produces="application/json")
  public ResponseEntity<Map<String, Object>> checkEmail(@RequestBody Map<String, Object> params){
   
   return userService.checkEmail(params); 
    
   // 리턴값 잘 나오는지 먼저 확인...return new ResponseEntity<>(HttpStatus.OK); // 요렇게 생략한 모습으로 넘길 수 있음. generic들은 생성시점의 타입은 생략할 수 있다.
  }
  
  @PostMapping(value="/sendCode.do", produces="application/json")
  public ResponseEntity<Map<String, Object>> sendCode(@RequestBody Map<String, Object> params){
   return userService.sendCode(params);
  }
  
  @PostMapping("/signup.do")
  public void signup(HttpServletRequest request, HttpServletResponse response) {
    userService.signup(request, response);
  }
  
  @GetMapping("/leave.do")
  public void leave(HttpServletRequest request, HttpServletResponse response) {
   // 1. request로 session 가져오기 2. HttpSession 바로 선언하기 3. @SessionAttribute(name="user") UserDto user로 가져오기
   // 2번의 경우 UserDto user = (UserDto) session.getAttribute("user"); 이렇게~ 난 1번과 2번의 혼종을 썼구나....
   // 여기선 첫번째 껄로 합니다. 3번 궁금한데 나중에 한번 해보기
    
    userService.leave(request, response);
    
    
    
  }
  
  
  
}
