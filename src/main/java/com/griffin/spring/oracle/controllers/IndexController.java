package com.griffin.spring.oracle.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class IndexController {
	
	/*
	 * Base URL methods
	 */
	@RequestMapping(method=RequestMethod.GET)
	public Object indexResponse() {
		return "Welcome to the Sample API";
	}
	
	
}
