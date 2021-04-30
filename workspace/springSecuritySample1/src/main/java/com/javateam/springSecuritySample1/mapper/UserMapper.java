/**
 * 
 */
package com.javateam.springSecuritySample1.mapper;

import org.apache.ibatis.annotations.Param;

import com.javateam.springSecuritySample1.vo.Role;
import com.javateam.springSecuritySample1.vo.Users;

/**
 * mapper
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