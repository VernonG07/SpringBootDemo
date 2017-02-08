package com.griffin.spring.oracle.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.griffin.spring.oracle.daos.UserDao;
import com.griffin.spring.oracle.models.Status;
import com.griffin.spring.oracle.models.User;
import com.griffin.spring.oracle.models.Status.StatusType;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	private Object createUser(HttpServletResponse response) {
		
		try {
			List<User> users = new UserDao(jdbcTemplate).getAllUsers();
			log.info("USERS: " + users.size());
			return users;
		} catch(Exception e) {
			log.error(e.getMessage());
			response.setStatus(404);
			return new Status(StatusType.ERROR, "could not retrieve users");
		}
	}
	
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	private Object getUser(@PathVariable("userId") int userId, HttpServletResponse response) {
		
		try {
			return new UserDao(jdbcTemplate).getUser(userId);
		} catch(Exception e) {
			log.error(e.getMessage());
			response.setStatus(404);
			return new Status(StatusType.ERROR, "could not find user");
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.PUT)
	private Object createUser(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, HttpServletResponse response) {
		
		try {
			new UserDao(jdbcTemplate).createUser(firstName, lastName);
			return new Status(StatusType.SUCCESS, "user created");
		} catch(Exception e) {
			log.error(e.getMessage());
			response.setStatus(404);
			return new Status(StatusType.ERROR, "could not create user");
		}
	}
}
