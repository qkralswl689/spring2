/**
 * 
 */
package com.javateam.springTranscationAnnotation.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.springTransactionAnnotation.dao.TransactionDAOMyBatis;

import lombok.extern.slf4j.Slf4j;

/**
 * @author javateam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@WebAppConfiguration 
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
@Slf4j
public class DeleteTest {
	
	@Autowired
	private TransactionDAOMyBatis dao;
	
	@Test
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Rollback(true) // DB에 반영하지 않고 rollback !
	public void testTransaction() {
		
		log.info("######### 트랜잭션 delete 테스트 ##############");
		
		dao.deleteMember("java1234");
	} //

}
