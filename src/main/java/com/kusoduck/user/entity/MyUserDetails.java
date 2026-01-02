package com.kusoduck.user.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    /**
	 *
	 */
	private static final long serialVersionUID = -3052071428676428492L;

	private int userId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        userId = user.getId();
        username = user.getName();
        password = user.getPassword();
        authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String getUsername() { return username; }

    @Override
    public String getPassword() { return password; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}

