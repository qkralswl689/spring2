package com.javateam.SpringMember.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.SpringMember.dao.MemberDao;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
		"classpath*:/spring/*-context.xml"})
@WebAppConfiguration
public class GetMemberTest {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private MemberDao dao;
	
	@Test
	public void test1() {
		
		// Mapper 단위 테스트
		assertNotNull(sqlSession.selectOne("mapper.MemberMapper.getMember", "javajava"));
	}
	
	@Test
	public void test2() {
		
		log.debug("########### dao getMember ###############");
		
		// DAO 단위 테스트
		assertEquals("가산동네사람", dao.getMember("javajava").getMemberNickname());
	}
	
}