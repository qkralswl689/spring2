package com.javateam.springTransactionAnnotation.service;

import java.util.List;

import com.javateam.springTransactionAnnotation.domain.MemberVO;

public interface TransactionServiceMyBatis {

	void insertMember(MemberVO member);
	MemberVO getMember(String id);
	void updateMember(MemberVO member);
	void deleteMember(String id);
	List<MemberVO> getAllMembers();
}