package com.gdu.prj09.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj09.dto.AddressDto;
import com.gdu.prj09.dto.MemberDto;

public interface MemberDao {
  int insertMember(MemberDto member);
  int insertAddress(AddressDto address);
  int updateMember(MemberDto member);
  int deleteMember(int memberNo);
  int deleteMembers(List<String> memberNoList); //db에서 1 = '1' 이기 때문에 String 으로 와도 ㄱㅊㄱㅊ 파라미터 받아올때 덜 귀찮기도 하고? 아마
  int getTotalMemberCount();
  List<MemberDto> getMemberList(Map<String, Object> map);
  MemberDto getMemberByNo(int memberNo);
  
  
  
  
  
  
}
