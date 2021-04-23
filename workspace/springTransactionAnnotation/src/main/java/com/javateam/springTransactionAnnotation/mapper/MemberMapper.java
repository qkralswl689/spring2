/**
 * 
 */
package com.javateam.springTransactionAnnotation.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.javateam.springTransactionAnnotation.domain.MemberVO;

/**
 * @author javateam
 *
 */
public interface MemberMapper {
	
	void insertMember(MemberVO member);
	void updateMember(MemberVO member);
	List<MemberVO> getAllMembers();
	MemberVO getMember(String id);
	void deleteMember(String id);

}