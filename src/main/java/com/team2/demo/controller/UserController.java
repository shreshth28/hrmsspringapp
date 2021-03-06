package com.team2.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team2.demo.model.MyUser;
import com.team2.demo.security.JwtUtil;
import com.team2.demo.service.UserService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserService userDetailsService;

	// test method, delete afterwards

	@GetMapping("/hello")
	public String hello() {
		log.info("hello");
		return "Hello";
	}

	@PostMapping("/login")  // 1
	public String login(@RequestBody MyUser myUser) {
		log.info("login");
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(myUser.getUsername(), myUser.getPassword()));
		} catch (BadCredentialsException bce) {
			log.error(bce.getMessage());
		} catch (AuthenticationException ae) {
			log.error(ae.getMessage());
		}
		log.info("user authenticated");
		return jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(myUser.getUsername())); // related to JWT 
	}

}