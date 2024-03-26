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
  List<AddressDto> getMemberList(Map<String, Object> map);
  MemberDto getMemberByNo(int memberNo);
  int getTotalAddressCountByNo(int memberNo);
  List<AddressDto> getAddressListByNo(Map<String, Object> map);  //begin end memberNo 이렇게 3가지가 map에 들어오게 됨
  
  // 나중에는 매개변수를 싹다 map 으로 통일하기도 합니다. 그럼 중간에 요구사항이나 변경사항이 생겨도 매개변수나 반환타입을 바꿀 필요가 없음.
  
  
  
  
  
}
