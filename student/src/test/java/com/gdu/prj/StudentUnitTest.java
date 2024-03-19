package com.gdu.prj;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.prj.dao.StudentDao;
import com.gdu.prj.dto.StudentDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class StudentUnitTest {

  private StudentDao studentDao;
  
  @Autowired
  public void setStudentDao(StudentDao studentDao) {
    this.studentDao = studentDao;
  }
  
  @Test 
  public void test01_등록() {
    
    StudentDto student = StudentDto.builder()
                             .name("테스트")
                             .korean(0000)
                             .english(000)
                             .math(0000)
                             .build();
    int inserCount = studentDao.insertStudent(student);
    
    assertEquals(1, inserCount);
    
  }
  
  
  
  
  
  
  
  
}
