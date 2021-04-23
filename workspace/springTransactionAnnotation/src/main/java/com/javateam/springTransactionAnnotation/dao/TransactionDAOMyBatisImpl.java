package com.javateam.springTransactionAnnotation.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.springTransactionAnnotation.domain.MemberVO;
import com.javateam.springTransactionAnnotation.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Repository 
@Slf4j
public class TransactionDAOMyBatisImpl implements TransactionDAOMyBatis {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insertMember(final MemberVO member) {
		
		log.info("insertMember");
		
		sqlSessionTemplate.getMapper(MemberMapper.class)
						  .insertMember(member);						

	} // insertMember

	@Override
	public MemberVO getMember(String id) {
		
		log.info("getMember");
		
		return sqlSessionTemplate.getMapper(MemberMapper.class)
						 		 .getMember(id);
	} // 

	@Override
	public void updateMember(final MemberVO member) {
		
		log.info("updateMember");
		
		MemberMapper memberMapper = sqlSessionTemplate.getMapper(MemberMapper.class);
		memberMapper.updateMember(member);
	} //

	@Override
	public void deleteMember(final String id) {
		
		log.info("deleteMember");
		
		MemberMapper memberMapper = sqlSessionTemplate.getMapper(MemberMapper.class);
		memberMapper.deleteMember(id);
	} //

	@Override
	public List<MemberVO> getAllMembers() {

		log.info("getAllMembers");
		
		MemberMapper memberMapper = sqlSessionTemplate.getMapper(MemberMapper.class);
		return memberMapper.getAllMembers();
	} //
	
} // class 