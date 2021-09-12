package com.ldapsecurity.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;

import com.ldapsecurity.modal.UserDetails;

@Service
public interface UserService {

	public UserDetails getUserDetails(LdapUserDetailsImpl principal,Collection<GrantedAuthority> authorities);

}
