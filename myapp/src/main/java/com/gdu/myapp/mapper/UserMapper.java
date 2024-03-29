package com.gdu.myapp.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.gdu.myapp.dto.UserDto;


@Component
public interface UserMapper {
  UserDto getUserByMap(Map<String, Object> map);
  int insertAccessHistory(Map<String, Object>map);
  int insertUser(UserDto user);
  
  
  
}
