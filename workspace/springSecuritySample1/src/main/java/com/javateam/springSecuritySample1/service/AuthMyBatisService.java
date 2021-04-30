/**
 * 
 */
package com.javateam.springSecuritySample1.service;

import com.javateam.springSecuritySample1.vo.Users;

/**
 * @author javateam
 *
 */
public interface AuthMyBatisService {
	
	boolean hasUsername(String username);
	void insertUsers(Users users, String role);

} //