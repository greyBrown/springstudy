package com.gdu.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.myapp.service.UploadService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/upload")
@RequiredArgsConstructor
@Controller
public class UploadController {

   private final UploadService uploadService;
   
   @GetMapping("/list.do")
   public String list(HttpServletRequest request, Model model) {
     model.addAttribute("request", request);
     uploadService.loadUploadList(model);   //즉 이 model에 request가 들어있고 model이 그걸 가지고 간다.
     return "upload/list";
   }
   
   @GetMapping("/write.page")
   public String write() {
     return "upload/write";
   }
   
   @PostMapping("/register.do")
   public String register(MultipartHttpServletRequest multipartRequest
                         , RedirectAttributes redirectAttributes) {
     redirectAttributes.addFlashAttribute("inserted", uploadService.registerUpload(multipartRequest)); //count를 받아오는게 아니라 되었나 안되었나를 true/false로 받아옴
     return "redirect:/upload/list.do";
   }
   
   @GetMapping("/detail.do")
   public String detail(@RequestParam(value="uploadNo", required = false, defaultValue = "0") int uploadNo
                         ,Model model) {
     uploadService.loadUploadByNo(uploadNo, model);  // 모델은 빈 통임. 여기서 정보 담아서 가져다줘~ 서비스가 이 모델에 정보를 가져다주고, 이 모델에 담겨온 정보를 뷰단에 뿌린다.
     return "upload/detail";
   }
   
   @GetMapping("/download.do")
   public ResponseEntity<Resource> download(HttpServletRequest request) {
     return uploadService.download(request);
   }
   
}
