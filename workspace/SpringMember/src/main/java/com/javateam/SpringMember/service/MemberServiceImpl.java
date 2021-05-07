package com.javateam.SpringMember.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.SpringMember.dao.MemberDao;
import com.javateam.SpringMember.domain.MemberVO;
import com.javateam.SpringMember.domain.Users;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private AuthMyBatisService authMyBatisService;

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public void insertMember(MemberVO member) {
		
		log.info("svc insertMember");
		dao.insertMember(member);
		
		// 패쓰워드 암호화
		BCryptPasswordEncoder passwordEncoder 
			= new BCryptPasswordEncoder(12);
		String hashedPassword 
			= passwordEncoder.encode(member.getMemberPassword());
		
		Users users = new Users();
		users.setUsername(member.getMemberId());
		users.setPassword(hashedPassword);
		users.setEnabled(1);
		
		// 암호화 패쓰워드 및 롤(Role) 정보 저장
		authMyBatisService.insertUsers(users, "ROLE_USER");
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isMember(String id) {
		
		log.info("svc isMember");
		return dao.isMember(id);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isEnableEmail(String email) {
		
		log.info("svc isEnableEmail");
		return dao.isEnableEmail(email);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isEnablePhone(String phone) {

		log.info("svc isEnablePhone");
		return dao.isEnablePhone(phone);
	}

}