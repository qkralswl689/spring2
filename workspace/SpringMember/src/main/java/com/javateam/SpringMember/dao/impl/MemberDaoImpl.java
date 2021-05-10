package com.javateam.SpringMember.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.SpringMember.dao.MemberDao;
import com.javateam.SpringMember.domain.MemberVO;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertMember(MemberVO member) {

		log.info("dao insertMember");
		sqlSession.insert("mapper.MemberMapper.insertMember", member);
	}

	@Override
	public boolean isMember(String id) {

		log.info("dao isMember");
		return (int)sqlSession.selectOne("mapper.MemberMapper.isMember", id) == 1 ? true : false;
	}

	@Override
	public boolean isEnableEmail(String email) {
		
		log.info("dao isEnableEmail");
		return (int)sqlSession.selectOne("mapper.MemberMapper.isEnableEmail", email) == 1 ? true : false; 
	}

	@Override
	public boolean isEnablePhone(String phone) {

		log.info("dao isEnablePhone");
		return (int)sqlSession.selectOne("mapper.MemberMapper.isEnablePhone", phone) == 1 ? true : false;
	}
	
	@Override
	public MemberVO getMember(String memberId) {

		log.debug("dao getMember");
		return sqlSession.selectOne("mapper.MemberMapper.getMember", memberId);
	}

}