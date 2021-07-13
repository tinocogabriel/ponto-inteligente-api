package com.aulaspringboot.pontointeligente.api.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {

	private static final String SENHA = "123456";
	private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
	
	@Test
	public void testSenhaNula() throws Exception{
		assertNull(PasswordUtils.geraBCrypt(null));
	}
	
	@Test 
	public void testGerarHashSenha() throws Exception {
		String hash = PasswordUtils.geraBCrypt((SENHA));
		
		assertTrue(bCryptEncoder.matches(SENHA, hash));
	}
}
