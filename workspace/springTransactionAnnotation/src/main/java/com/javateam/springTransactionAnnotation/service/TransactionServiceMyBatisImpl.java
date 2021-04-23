package com.javateam.springTransactionAnnotation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.springTransactionAnnotation.dao.TransactionDAOMyBatis;
import com.javateam.springTransactionAnnotation.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Service
// @Transactional(propagation = Propagation.REQUIRED, 
//                rollbackFor = Exception.class)
@Slf4j
public class TransactionServiceMyBatisImpl implements TransactionServiceMyBatis {
	
	@Autowired
	private TransactionDAOMyBatis dao;

	@Transactional(propagation = Propagation.REQUIRED, 
			       rollbackFor = Exception.class)
	@Override
	public void insertMember(MemberVO member) {

		log.info("Tx service insertMember");
		dao.insertMember(member);

	}

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	@Override
	public MemberVO getMember(String id) {

		log.info("Tx service getMember");
		return dao.getMember(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, 
		           rollbackFor = Exception.class)
	@Override
	public void updateMember(MemberVO member) {
		
		log.info("Tx service updateMember");
		dao.updateMember(member);
	}

	@Transactional(propagation = Propagation.REQUIRED, 
		   	   	   rollbackFor = Exception.class)
	@Override
	public void deleteMember(String id) {

		log.info("Tx service deleteMember");
		dao.deleteMember(id);
	}

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	@Override
	public List<MemberVO> getAllMembers() {

		log.info("Tx service getAllMembers");
		return dao.getAllMembers();
	}

}