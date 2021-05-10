package com.javateam.SpringMember.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.javateam.SpringMember.domain.CustomUser;
import com.javateam.SpringMember.domain.Role;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class CustomProvider 
	implements AuthenticationProvider, UserDetailsService {
	
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
    @Override
	public CustomUser loadUserByUsername(String userName) {
    	
    	log.debug("loadUserByUsername");
    	
    	try {
	    	return (CustomUser)jdbcTemplate.queryForObject(
	    			 "SELECT * FROM users WHERE username=?", 
				     new Object[]{ userName },
				     new BeanPropertyRowMapper<CustomUser>(CustomUser.class));
	    } catch (EmptyResultDataAccessException e) {
	    	log.error("error1");
	    	return null;
	    }

    }
	
	private Role loadUserRole(String userName) {
		
		log.debug("loadUserRole");
    	
		try {
			return (Role)jdbcTemplate.queryForObject(
	   			 	"SELECT username, role FROM user_roles WHERE username=?", 
				     new Object[]{ userName },
				     new BeanPropertyRowMapper<Role>(Role.class));
		} catch (EmptyResultDataAccessException e) {
			log.error("error2");
	    	return null;
	    }
		
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) 
				throws AuthenticationException {
		
		log.debug("###### 로그인 인증 점검 : authenticate ######");
		
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        CustomUser user = null;
        Collection<? extends GrantedAuthority> authorities = null;
        
        try {
	        	user = this.loadUserByUsername(username);
	            Role role = this.loadUserRole(username);
	            
	            if (user == null) {
	            	throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
	            }
	            
	            List<Role> roles = new ArrayList<Role>();
	            roles.add(role);		
	            user.setAuthorities(roles);
	            
	            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12); 
	            
	            if (passwordEncoder.matches(password, user.getPassword())) 
	            	log.debug("비밀번호 일치 !");	
	            else 
	            	throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
	            
	            authorities = user.getAuthorities();
            
        } catch(UsernameNotFoundException e) {
            log.error(e.toString());
            throw new UsernameNotFoundException(e.getMessage());
        } catch(BadCredentialsException e) {
            log.error(e.toString());
            throw new BadCredentialsException(e.getMessage());
        } catch(Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

} //