package com.gdu.myapp.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {

  boolean registerUpload(MultipartHttpServletRequest multipartRequest);
  void loadUploadList(Model model);  // 원래 리스트는 보통 request 와 model이 넘어가지만 이번에는 model만 넘어가는 방식으로
  void loadUploadByNo(int uploadNo, Model model);
  ResponseEntity<Resource> download(HttpServletRequest request);
  ResponseEntity<Resource> downloadALL(HttpServletRequest request);
  //다운로드 할 때 주소 안바뀐다 ->SPA 임. SPA AJAX 를 위한 ResponseEntity
  
  
  
  
  
  
  
  
  
}
