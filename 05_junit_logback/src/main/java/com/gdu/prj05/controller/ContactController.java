package com.gdu.prj05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.prj05.service.ContactService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/contact")
@RequiredArgsConstructor
public class ContactController {

  private final ContactService contactService;
    
    @GetMapping(value="/list.do")
    public String list(Model model) {
      model.addAttribute("contactList", contactService.getContactList());
      return "contact/list";      // view Resolver
    }
    
    @GetMapping(value="/detail.do")
    public String detail(@RequestParam(value="contect-no", required=false, defaultValue = "0") int contactNo
                                                                                            ,Model model) {
      // 만약 파라미터가 비었으면 0이라고 처리하겠다. (DB 상에 0 이 없으니 상세보기 못하고 끝)
      model.addAttribute("contact", contactService.getContactByNo(contactNo));
      return "contact/detail";
    }
  
  
  
  
}
