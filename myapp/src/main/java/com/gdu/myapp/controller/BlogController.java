package com.gdu.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.myapp.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/blog")
@Controller
@RequiredArgsConstructor
public class BlogController {
  
  private final BlogService blogService;
  
  @GetMapping("/list.page")
  public String list() {
    return "blog/list";
    // 평소에는 여기서 service를 불렀지만...스크롤에서는 그냥 단순 페이징 이동("/list.page")만 함. header랑 write 도 do로 해놨었으니까 바꿔줌.
    //왜? ajax으로 가져올 것이기 때문에. 페이지 이동 후 ajax이 한번 돌면서 최초 목록을 뿌려줌. 휠을 내리면 그다음 목록 뿌리고...이런식으로
  }
  
  @GetMapping("/write.page")
  public String writePage() {
    return "blog/write";
  }
  
  @PostMapping(value="/summernote/imageUpload.do", produces = "application/json")
  public ResponseEntity<Map<String, Object>> summernoteImageUpload(@RequestParam("image") MultipartFile multipartFile){
    return blogService.summernoteImageUpload(multipartFile);
  }
  
  @PostMapping("/register.do")
  public String register(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("insertCount", blogService.registerBlog(request));
    return "redirect:/blog/list.page";
  }
  
  @GetMapping(value="/getBlogList.do", produces="application/json")
  public ResponseEntity<Map<String, Object>> getBlogList(HttpServletRequest request){
    return blogService.getBlogList(request);
  }
  
  
  @GetMapping("/detail.do")
  public String detail(@RequestParam int blogNo, Model model) {
    model.addAttribute("blog", blogService.getBlogByNo(blogNo));
    return "blog/detail";
  }
  
  @PostMapping(value="/registerComment.do", produces = "application/json")
  public ResponseEntity<Map<String, Object>> registerComment(HttpServletRequest request){
    System.out.println(request.getParameter("contents"));
    System.out.println(request.getParameter("blogNo"));
    System.out.println(request.getParameter("userNo"));
    return new ResponseEntity<>(null);
  }
  
  
  
}
