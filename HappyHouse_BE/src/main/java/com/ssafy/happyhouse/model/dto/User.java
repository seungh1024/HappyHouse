package com.ssafy.happyhouse.model.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements UserDetails {

	private long idx;
	private String name;
	private String email;
	private String password;
	private String role;

	public User(long idx, String name, String email, String password, String role) {
		this.idx = idx;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User() {
	}

	public User(String email, String password, String name) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new SimpleGrantedAuthority(this.role));
		return collection;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
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

	@Override
	public String toString() {
		return "User [idx=" + idx + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ "]";
	}

}