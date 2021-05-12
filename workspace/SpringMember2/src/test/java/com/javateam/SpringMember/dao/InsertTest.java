package com.javateam.SpringMember.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.SpringMember.dao.MemberDao;
import com.javateam.SpringMember.domain.MemberDTO;
import com.javateam.SpringMember.domain.MemberVO;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
		"classpath*:/spring/*-context.xml"})
@WebAppConfiguration
@Log4j2
public class InsertTest {
	
	@Autowired
	private MemberDao dao;
	
	private MemberDTO memberDTO;
	private MemberVO memberVO;
	
	@Before
	public void setUp() {
		
		memberDTO = new MemberDTO();
		
		memberDTO.setMemberId("abcd1234123");
		memberDTO.setMemberPassword("@Abcd1234");
		memberDTO.setMemberNickname("홍길홍길");
		memberDTO.setMemberName("홍길동");
		memberDTO.setMemberPhone("010-1234-7777");
		memberDTO.setMemberZip("12345");
		memberDTO.setMemberGender("m");
		memberDTO.setMemberEmail("abcd123123@abcd.com");
		memberDTO.setMemberBirth("2000-05-22");
		//memberDTO.setMemberAddressBasic("서울 구로 가산");
		//memberDTO.setMemberAddressDetail("koitt");
		memberDTO.setMemberAddressBasic("");
		memberDTO.setMemberAddressDetail("");
		
		memberVO = new MemberVO(memberDTO);
		
		log.info("회원 정보(DTO) : "+memberDTO);
		log.info("회원 정보(VO) : "+memberVO);
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Rollback(true) 
	public void test() {

		log.info("###### 회원정보 생성 단위 테스트 #######");
		
		dao.insertMember(memberVO);
		
	} //

}
