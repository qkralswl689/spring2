/**
 * 
 */
package com.javateam.SpringMember.service;

import com.javateam.SpringMember.domain.Users;

/**
 * @author javateam
 *
 */
public interface AuthMyBatisService {
	
	/**
	 * 회원 아이디 존재 여부 점검
	 * 
	 * @param username 회원 아이디
	 * @return 회원 존재 여부
	 */
	boolean hasUsername(String username);
	
	/**
	 * 회원 인증 정보(아이디/암호화 패쓰워드, 등급(롤(Role)) 등 포함) 생성(삽입)
	 * 
	 * @param users 회원 인증 정보(아이디/암호화 패쓰워드 포함)
	 * @param role 회원 등급(롤(role)) 정보
	 */
	void insertUsers(Users users, String role);

	// 추가 : 패쓰워드 갱신(수정)
	/**
	 * 회원 인증 정보 수정
	 * 
	 * @param users 수정할 회원 인증 정보 객체
	 */
	void updateUsers(Users users);
	
	// 추가 : 회원 삭제
	/**
	 * 회원 인증 정보 삭제
	 * 
	 * @param username 삭제할 회원 아이디
	 */
	void deleteUsers(String username);

} //