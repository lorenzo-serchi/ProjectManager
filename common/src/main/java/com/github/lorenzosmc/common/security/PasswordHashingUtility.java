package com.github.lorenzosmc.common.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class PasswordHashingUtility {
	//FIXME don't hardcode
	private final int BCRYPT_STRENGTH = 14;
		
	private BCryptPasswordEncoder passwordEncoder;
	
	public PasswordHashingUtility() {
		SecureRandom rng = new SecureRandom();
		passwordEncoder = new BCryptPasswordEncoder(BCRYPT_STRENGTH, rng);
	}
	
	
	//TODO check password characteristics (min/max length, characters, ...) so bCrypt doesnt break
	public String hashPassword(String password){
		return passwordEncoder.encode(password);
	}
		
	public boolean verifyPassword(String inputPassword, String passwordHash) {
		return passwordEncoder.matches(inputPassword, passwordHash);
	}
}
