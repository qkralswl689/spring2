package com.javateam.SpringMember.service;

import com.javateam.SpringMember.domain.MemberVO;

public interface MemberService {
	
	/**
	 * 회원 가입(회원정보 생성) 처리
	 * @param member 회원정보(객체)
	 */
	void insertMember(MemberVO member);
	
	/**
	 * 회원 존재 여부 점검(회원 아이디 중복 점검)
	 * @param id 회원 아이디
	 * @return 회원 존재 여부
	 */
	boolean isMember(String id);
	
	/**
	 * 이메일 중복 점검
	 * @param email이메일
	 * @return 이메일 중복 여부
	 */
	boolean isEnableEmail(String email);
	
	/**
	 * 휴대폰 중복 점검
	 * @param phone 휴대전화 번호
	 * @return 휴대폰 중복 여부
	 */
	boolean isEnablePhone(String phone);
	
	/**
	 * 개별 회원정보 레코드(튜플) 조회(read)
	 * 
	 * @param memberId 회원 아이디
	 * @return 회원정보
	 * @throws Exception 예외처리
	 */
	MemberVO getMember(String memberId);
		
}