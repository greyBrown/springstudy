package com.gdu.prj09.service;

import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.prj09.dao.MemberDao;
import com.gdu.prj09.dto.AddressDto;
import com.gdu.prj09.dto.MemberDto;
import com.gdu.prj09.utils.MyPageUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberDao memberDao;
  private final MyPageUtils myPageUtils;
  // root-context 에서 con-arg에 이거 넣어줄 때 선언한 순서대로 넣어줘야 한다.  
  
  @Override
  public ResponseEntity<Map<String, Object>> getMembers(int page, int display) {

    int total = memberDao.getTotalMemberCount();
    
    myPageUtils.setPaging(total, display, page);
    
    Map<String, Object> params = Map.of("begin", myPageUtils.getBegin() 
                                        , "end", myPageUtils.getEnd());
    
    List<AddressDto> members = memberDao.getMemberList(params);
    
    return new ResponseEntity<Map<String,Object>>(Map.of("members", members
                                                         , "total", total
                                                         , "paging", myPageUtils.getAsyncPaging()) 
                                                       , HttpStatus.OK);
    
  }

  @Override
  public ResponseEntity<Map<String, Object>> getMemberByNo(int memberNo) {

    //address 목록 가져오기 위한 작업
    int total = memberDao.getTotalAddressCountByNo(memberNo);
    int page = 1;
    int display = 20;
    
    myPageUtils.setPaging(total, display, page);
    
    Map<String, Object> params = Map.of("memberNo", memberNo
                                       , "begin", myPageUtils.getBegin()
                                       , "end", myPageUtils.getEnd());
    
    // 주소 목록을 가져옴
    List<AddressDto> addressList = memberDao.getAddressListByNo(params);
    
    // 회원 정보
    MemberDto member = memberDao.getMemberByNo(memberNo);
    
    return new ResponseEntity<Map<String,Object>>(Map.of("addressList", addressList
                                                         , "member", member)
                                               , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Map<String, Object>> registerMember(Map<String, Object> map, HttpServletResponse response) {
    
    
    ResponseEntity<Map<String, Object>> result = null;
    
    try {
      
      MemberDto member = MemberDto.builder()
          .email((String)map.get("email"))  // 이게 맵에서 데이터 빼서 쓰는 방법!! object 타입이니까 이렇게 변환
          .name((String)map.get("name"))
          .gender((String)map.get("gender"))
          .build();
      
      int insertCount = memberDao.insertMember(member);           //insertCount 에서 1이나 0을 받아내는게 member.jsp 의 resData.
      
      AddressDto address = AddressDto.builder()
                             .zonecode((String)map.get("zonecode"))
                             .address((String)map.get("address"))
                             .detailAddress((String)map.get("detailAddress"))
                             .extraAddress((String)map.get("extraAddress"))
                             .member(member)
                          .build();
          
      insertCount += memberDao.insertAddress(address);
      
      
      result = new ResponseEntity<Map<String,Object>>(
          Map.of("insertCount", insertCount),
          HttpStatus.OK);  
      
    } catch (DuplicateKeyException e) {   // catch(Exception e) { 이름확인 : e.getClass().getName() } 를 통해서 무슨 예외가 던져지는지 확인할 수 있다.
      
      try {
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("이미 가입된 이메일입니다.");  //jqXHR 객체의 responseText 속성으로 확인 가능
        out.flush();
        out.close();
        
        
      } catch (Exception e2) {
        e.printStackTrace();
      }
      
    }
    
    return result;
    
    }

  @Override
  public ResponseEntity<Map<String, Object>> modifyMember(MemberDto member) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseEntity<Map<String, Object>> removeMember(int memberNo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseEntity<Map<String, Object>> removeMembers(String memberNoList) {
    // TODO Auto-generated method stub
    return null;
  }

}
