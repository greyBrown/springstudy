package com.gdu.prj.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.gdu.prj.dto.StudentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {
  
    
    private final SqlSessionTemplate sqlSessionTemplate;
    
    public final static String NS = "com.gdu.prj.mybatis.mapper.student_t."; 
    
  
  @Override
  public int insertStudent(StudentDto student) {
    int insertCount = sqlSessionTemplate.insert(NS + "registerContact", student);
    return insertCount;
  }

  @Override
  public int updateStudent(StudentDto student) {
    int updateCount = sqlSessionTemplate.update(NS + "modifyContact", student);
    return updateCount;
  }

  @Override
  public List<StudentDto> selectAll(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<StudentDto> selectByScore(int begin, int end) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void close() {
    // TODO Auto-generated method stub

  }

}