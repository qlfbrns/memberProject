package com.hk.member.mapper;

import java.util.List;

import com.hk.member.vo.Member;

public interface MemberMapper {
	
	//CRUD를 구현(Create, Read or Retrive, Update, Delete)
	//1.전체 리스트
	//@Select("select * from members")
	public List<Member> memberList();
	
	//2.한개만 보기
	public Member memberGetOne(int mno);
	
	//3.한개만 insert하기
	public int memberRegister(Member member);
	
	//4.수정
	public int memberUpdate(Member member);
	
	//5.삭제
	public int memberDelete(int mno);

	//6.로그인확인
	public Member exist(Member member);
	
}
