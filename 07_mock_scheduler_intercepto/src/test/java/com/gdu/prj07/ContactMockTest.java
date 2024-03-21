package com.gdu.prj07;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringJUnit4ClassRunner.class)


// ContactService 타입의 contactServiceImpl bean 이 등록된 파일
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})


// WebApplicationContext 타입의 bean을 Spring Container 에 만든다.
@WebAppConfiguration

public class ContactMockTest {
  
  @Autowired
  private WepApplicationContext webApplicationContext;
  
  // 테스트 수행을 위한 MockMvc 객체 선언
  private MockMvc mockMvc; // 위의 @Autowired와는 상관이 없다. 이건 지금은 그냥 null값임.
  
  // MockMvc 객체 생성 (테스트 수행 이전에 생성한다)
  @Before
  


}
