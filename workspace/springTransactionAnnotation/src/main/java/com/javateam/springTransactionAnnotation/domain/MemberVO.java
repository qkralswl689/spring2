/**
 * 
 */
package com.javateam.springTransactionAnnotation.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VO : entity
 * @author javateam
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	
	// 멤버 필드들
	private String id; // 아이디
	private String pw; // 패쓰워드
	private String name; // 이름
	private String address; // 주소
	private Date joindate; // 가입일
	
	/**
	 * 필수 멤버 필드 초기화
	 * 
	 * @param id 아이디
	 * @param pw 패쓰워드
	 * @param name 이름
	 */
	public MemberVO(String id, 
				    String pw, 
				    String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

}