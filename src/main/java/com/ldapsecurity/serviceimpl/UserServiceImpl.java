package com.ldapsecurity.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;

import com.ldapsecurity.modal.UserDetails;
import com.ldapsecurity.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserDetails getUserDetails(LdapUserDetailsImpl principal, Collection<GrantedAuthority> authorities) {
		UserDetails userDetails = new UserDetails();
		List<String> rolesList = new ArrayList<String>();
		authorities.stream().forEach(authority -> {
			rolesList.add(authority.getAuthority());
		});
		userDetails.setUserName(principal.getUsername());
		userDetails.setuID(principal.getDn());
		userDetails.setRole(rolesList);
		return userDetails;
	}
}
