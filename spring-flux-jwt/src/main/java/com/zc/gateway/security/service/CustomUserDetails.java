package com.zc.gateway.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zc.gateway.security.model.User;

public class CustomUserDetails extends User implements UserDetails {
	
	List<String> rolenames = null;
	private static final long serialVersionUID = 7237565895023806436L;

	public CustomUserDetails(User user,List<String> rolenames) {
		super(user);
		this.rolenames=rolenames;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (this.rolenames != null) {
            for (String role : this.rolenames) {
                // ROLE_USER, ROLE_ADMIN,..
            
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
    	
        
		return grantList;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
