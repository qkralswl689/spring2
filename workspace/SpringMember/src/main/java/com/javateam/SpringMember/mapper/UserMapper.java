/**
 * 
 */
package com.javateam.SpringMember.mapper;

import org.apache.ibatis.annotations.Param;

import com.javateam.SpringMember.domain.Role;
import com.javateam.SpringMember.domain.Users;

/**
 * 사용자 인증(auth) 처리 mapper
 * 
 * @author javateam
 *
 */
public interface UserMapper {

	Users getUserByUsername(String userName);

	Role getUserRolesByUsername(String userName);
	
	int hasUsername(String username);
	
	Users loadUserByUsername(String userName);
	
	void insertUser(@Param("users") Users users);
	
	void insertUserRoles(@Param("username") String username, 
						 @Param("role") String role);

}