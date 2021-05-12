package com.javateam.SpringMember.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.SpringMember.dao.MemberDao;
import com.javateam.SpringMember.domain.MemberVO;
import com.javateam.SpringMember.domain.PageDTO;

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

		log.info("dao getMember");
		return sqlSession.selectOne("mapper.MemberMapper.getMember", memberId);
	}
	
	@Override
	public void updateMember(MemberVO member) {

		log.info("dao updateMember");
		sqlSession.update("mapper.MemberMapper.updateMember", member);
	}

	@Override
	public void deleteMember(String memberId) {

		log.info("dao deleteMember");
		sqlSession.delete("mapper.MemberMapper.deleteMember", memberId);
	}
	
	@Override
	public boolean isEnableEmail(String memberId, String email) {
		
		log.info("dao isEnableEmail");
		log.info("아이디 : "+memberId);
		log.info("이메일 : "+email);		
		
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("email", email);
		
		return (int)sqlSession.selectOne("mapper.MemberMapper.isEnableEmailUpdate", map) == 1 ? true : false;
	}

	@Override
	public boolean isEnablePhone(String memberId, String phone) {
		
		log.info("dao isEnablePhone");
		
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("phone", phone);
		
		return (int)sqlSession.selectOne("mapper.MemberMapper.isEnablePhoneUpdate", map) == 1 ? true : false;
	}
	
	@Override
    public List<MemberVO> getMembersByPaging(int page, int limit) {
 
        log.info("dao getMembersByPaging");
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setLimit(limit);
        return sqlSession.selectList("mapper.MemberMapper.getMembersByPaging", pageDTO);
    }
 
    @Override
    public List<MemberVO> getAllMembers() {
 
        log.info("dao getAllMembers");
        return sqlSession.selectList("mapper.MemberMapper.getAllMembers");
    }

}