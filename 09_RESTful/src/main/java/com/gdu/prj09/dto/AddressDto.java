package com.gdu.prj09.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressDto {
  private int addressNo;
  private String zonecode;
  private String address;
  private String detailAddress;
  private String extraAddress;
  private MemberDto member;         
  
  /*AddressDto
   * zone
   * addr
   * detail
   * extra
   * MemberDto(memberNo,email, name, gender)
   * 이렇게 집어 넣을거임
   * 
   * int memberNo를 들어내고 그 자리에 MemberDto를 넣음 -> 한 Dto에 json에서 넘겨주는 데이트를 받을 필드가 모두 존재하게 됨
   */   
}
