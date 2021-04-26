package com.javateam.SpringEhCacheDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.javateam.SpringEhCacheDemo.dao.EmployeesDAO;
import com.javateam.SpringEhCacheDemo.domain.EmployeesVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDAO dao;

	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Cacheable(value="samplePromotionCache")
	@Override
	public List<EmployeesVO> getAll() {

		log.info("getAll");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		List<EmployeesVO> members = null;

		try {
			
			members = dao.getAllEmployees();	
			transactionManager.commit(status);
			
		} catch (Exception e) {
			log.error("트랜잭션 에러 !");
			transactionManager.rollback(status);
		} //

		return members;
	} //

	// @Transactional
	// 참고) allEntries=true 또는 key="0" : 캐시의 모든 내용 갱신/삭제
	// 참고) 캐시 추상화 : https://blog.outsider.ne.kr/1094
	@CacheEvict(value="samplePromotionCache", allEntries=true)
	@Override
	public boolean updateCommissionPct(final float cp) {

		log.info("updateCommissionPct");
		boolean flag = false;
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	 
	    TransactionStatus status = transactionManager.getTransaction(def);
	    
	    try {
		    	dao.updateEmployeesCommissionPct(cp);
				flag = true;
				transactionManager.commit(status);
				
	    } catch (Exception e) {
	    	log.error("updateCommissionPct 트랜잭션 오류");
	    	transactionManager.rollback(status);
	    	flag = false;
	    } //
				
		return flag;
	} //

}
