package com.zsoft.security;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zsoft.dao.entities.User;
import com.zsoft.service.IUserService;

public class CustomUserDetailsService implements UserDetailsService {
	
	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		User user = userService.getUserByUsername(username);
		if (user != null) {
			
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

	        return new org.springframework.security.core.userdetails.User(
	        		user.getUsername(), user.getPassword(), true, true, true, true, grantedAuths);

		}
		
		return null;
	}

}
