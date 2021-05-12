/**
 * 
 */
package com.javateam.SpringMember.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.SpringMember.dao.AuthMyBatisDao;
import com.javateam.SpringMember.domain.Users;
import com.javateam.SpringMember.mapper.UserMapper;

import lombok.extern.log4j.Log4j2;

/**
 * @author javateam
 *
 */
@Repository
@Log4j2
public class AuthMyBatisDaoImpl implements AuthMyBatisDao {
	
	@Autowired
	private SqlSession sqlSession;

	/**
	 * @see com.javateam.SpringMember.service.AuthMyBatisService#hasUsername(java.lang.String)
	 */
	@Override
	public boolean hasUsername(String username) {

		log.info("hasUsername");
		
		return sqlSession.getMapper(UserMapper.class)
						 .hasUsername(username) == 1 ? true : false;
	} //

	/**
	 * @see com.javateam.SpringMember.service.AuthMyBatisService#insertUsers(com.javateam.springSecuritySample1.vo.Users, java.lang.String)
	 */
	@Override
	public void insertUsers(Users users, String role) {

		log.info("insertUsers");
		
		sqlSession.getMapper(UserMapper.class)
				  .insertUser(users);
		
		sqlSession.getMapper(UserMapper.class)
		  		  .insertUserRoles(users.getUsername(), role);
	} //
	
	@Override
	public void updateUsers(Users users) {

		log.info("updateUsers");
		sqlSession.getMapper(UserMapper.class).updateUser(users.getUsername(), users.getPassword());		
	}

	@Override
	public void deleteUsers(String username) {

		log.info("deleteUsers");
		sqlSession.getMapper(UserMapper.class).deleteUser(username);
	}

} //