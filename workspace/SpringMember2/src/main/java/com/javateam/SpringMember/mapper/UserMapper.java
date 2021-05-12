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

	// 추가
	Role loadUserRole(@Param("username") String useranme);
	
	// 추가 : 패쓰워드 갱신(수정)
	void updateUser(@Param("username") String username,
					@Param("password") String password);
	
	// 추가 : 회원 정보 삭제(패쓰워드, role)
	void deleteUser(String username);

}