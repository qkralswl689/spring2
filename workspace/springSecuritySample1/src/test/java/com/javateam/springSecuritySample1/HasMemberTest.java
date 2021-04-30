package com.javateam.springSecuritySample1;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Log4j2
public class HasMemberTest {
	
	String username;
	
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    public void setDataSource(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Before
	public void setUp() throws Exception {
		
		username = "java";
	}

	@Test
	public void test() {
		
		log.info("HasMemberTest");
		
		String sql = "SELECT count(*) FROM users WHERE username = ?";
    	
		int count = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
		
		log.info("count : " + count);
		assertEquals(count, 1);
		
	} //

}
