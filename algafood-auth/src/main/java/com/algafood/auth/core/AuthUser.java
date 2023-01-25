package com.algafood.auth.core;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;

import com.algafood.auth.domain.Usuario;

import lombok.Getter;

@Getter
public class AuthUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String fullName;
	
	public AuthUser(Usuario usuario) {
		super(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
		fullName = usuario.getNome();
	}
}
