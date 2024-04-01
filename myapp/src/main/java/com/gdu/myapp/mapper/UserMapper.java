package com.gdu.myapp.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.gdu.myapp.dto.LeaveUserDto;
import com.gdu.myapp.dto.UserDto;


@Component
public interface UserMapper {
  UserDto getUserByMap(Map<String, Object> map);
  int insertAccessHistory(Map<String, Object>map);
  LeaveUserDto getLeaveUserByMap(Map<String, Object> map);
  int insertUser(UserDto user);
  
  /* 인터페이스의 메소드 이름이 mapper의 메소드 메소드 이름이 된다.
   * java : mapper       mybatis: mapper
   *   Interface               xml
   *    method                 id
   *       namespace : Interface
   *                   
   * 요렇게 java의 mapper와 mybatis 의 mapper가 매칭이된다.                  
   * 
   * 
   * + 그리고 root-context 의 mybatis scan 에서 위치가 어디라고 알려주면 된다.
   */
  
}
