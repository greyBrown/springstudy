package com.gdu.prj09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.prj09.service.MemberService;

import lombok.RequiredArgsConstructor;

/*
 * RESTful
 * 1. Representation State Transfer
 * 2. 요청 주소를 작성하는 한 방식이다.
 * 3. 요청 파라미터를 ? 뒤에 추가하는 Query String 방식을 사용하지 않는다.
 * 4. 요청 파라미터를 주소에 포함하는 Path Variable 방식을 사용하거나, 요청 본문에 포함하는 방식을 사용한다.
 * 5. 요청의 구분을 "주소 + 메소드" 조합으로 구성한다.
 * 6. CRUD 요청 예시
 *              URL                        |  Method 
 *  1) 목록    /members                    |  GET
 *             /members/page1              |    
 *             /members/page1/display/20   |
 *  2) 상세    /members/1                  |  GET 
 *  3) 삽입    /members                    |  POST
 *  4) 수정    /members                    |  PUT
 *  4) 삭제    /members/1                  |  DELETE
 *             /members/1,2,3              |
 *             
 *  // 지난번에 form 데이터로 body에 실어 보냈다면 이번에는 json으로 만들것임. json이 더 쉬운 면도 있음
 *  // 방식이 삽입이면 POST 수정이면 PUT. 메소드(dao와 service 내부의..?)가 다르니까 방식(Method)이 바뀜. 하지만 내부동작은 POST와 유사함. 구분을 위해 이름이 다르다.
 *  // 주소는 같지만 Method 에서 구분이 된다.
 *  
 */            



@RequiredArgsConstructor
@Controller
public class MemberController {
  
   private final MemberService memberService;
   
   @GetMapping("/admin/member.do")
   public void adminMember() {        
     //반환타입이 void 인 경우  주소를 ("/admin/member.do")를 JSP 경로로 인식한다.
     // /admin/member.do   =====> /WEB-INF/views/admin/member.jsp
     
   }
   
   
   
  
  
  
  
  
  
  
  
  
  
  
}
