package com.gdu.prj.service;

import javax.servlet.http.HttpServletRequest;

import com.gdu.prj.common.ActionForward;

public interface StudentService {

  ActionForward addStudent(HttpServletRequest request);
  ActionForward modifyStudent(HttpServletRequest request);
  ActionForward getList(HttpServletRequest request);
  ActionForward getByScore(HttpServletRequest request);
  
}
