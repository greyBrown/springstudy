package com.gdu.myapp.service;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.gdu.myapp.dto.UserDto;
import com.gdu.myapp.mapper.UserMapper;
import com.gdu.myapp.utils.MySecurityUtils;

@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  //원래 인터페이스는 객체생성이 안되지만 마이바티스 업그레이드로 사용자 편의를 위해 지원된다.
  
//@Autowired 생략
  public UserServiceImpl(UserMapper userMapper) {
    super();
    this.userMapper = userMapper;
  }

  @Override
  public void signin(HttpServletRequest request, HttpServletResponse response) {
    
    try {  //try 할때 애매하면 싹다 집어넣기 ^-^
      
      String email = request.getParameter("email");
      String pw = MySecurityUtils.getSha256(request.getParameter("pw"));
      String ip = request.getRemoteAddr();
      
      Map<String, Object> params = Map.of("email", email
                                        , "pw", pw
                                        , "ip", ip);
      
      UserDto user = userMapper.getUserByMap(params);
      
      if(user != null) {
        userMapper.insertAccessHistory(params);
        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getParameter("url"));
      } else {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('일치하는 회원 정보가 없습니다.')");
        out.println("location.href='" + request.getContextPath() + "/main.page'");
        out.println("</script>");
        out.flush();
        out.close();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public void signout(HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub

  }

  @Override
  public void signup(HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub

  }

  @Override
  public void leave(HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub

  }

}
