/**
 * 
 */
package com.javateam.SpringMember.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.SpringMember.dao.AuthJdbcService;
import com.javateam.SpringMember.dao.AuthMyBatisDao;
import com.javateam.SpringMember.domain.Users;

/**
 * @author javateam
 *
 */
@Service
public class AuthMyBatisServiceImpl implements AuthMyBatisService {
	
	@Autowired
	private AuthMyBatisDao dao;
	
	@Transactional(readOnly=true)
	@Override
	public boolean hasUsername(String username) {
		return dao.hasUsername(username);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public void insertUsers(Users users, String role) {
		dao.insertUsers(users, role);
	}
	
	@Override
	public void updateUsers(Users users) {
		dao.updateUsers(users);
	}

	@Override
	public void deleteUsers(String username) {
		dao.deleteUsers(username);
	}

} //