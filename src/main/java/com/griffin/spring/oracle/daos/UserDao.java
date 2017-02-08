package com.griffin.spring.oracle.daos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.griffin.spring.oracle.models.User;

public class UserDao {

	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public User getUser(long id) throws Exception {
		
        List<User> users = jdbcTemplate.query(
                "SELECT id, firstname, lastname FROM person WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastName"))
        );
        
        if (users.size()<1 || users.size()>1) {
        	log.info("incorrect number of users: " + users.size());
        	throw new Exception();
        }
        
		
		return users.get(0);
	}
	
	public List<User> getAllUsers() {
		
        List<User> users = jdbcTemplate.query(
                "SELECT * FROM person", new Object[] {},
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastName"))
        );

		return users;
	}
	
	public void createUser(String firstName, String lastName) {
		jdbcTemplate.update("INSERT INTO person (firstname, lastname) VALUES (?,?)", new Object[]{firstName, lastName});
	}
	
}
