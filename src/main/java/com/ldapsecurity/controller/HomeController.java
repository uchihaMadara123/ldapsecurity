package com.ldapsecurity.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldapsecurity.modal.UserDetails;
import com.ldapsecurity.service.UserService;

@RestController
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public ResponseEntity<UserDetails> getUserInformation() {
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();
		LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();
		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		return new ResponseEntity<UserDetails>(userService.getUserDetails(principal, authorities), HttpStatus.OK);
	}
}