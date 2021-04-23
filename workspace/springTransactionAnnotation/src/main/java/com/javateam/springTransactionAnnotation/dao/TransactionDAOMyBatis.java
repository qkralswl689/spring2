package com.javateam.springTransactionAnnotation.dao;

import java.util.List;

import com.javateam.springTransactionAnnotation.domain.MemberVO;

/**
 * DAO
 * @author javateam
 *
 */
public interface TransactionDAOMyBatis {

	void insertMember(MemberVO member);
	MemberVO getMember(String id);
	void updateMember(MemberVO member);
	void deleteMember(String id);
	List<MemberVO> getAllMembers();

}