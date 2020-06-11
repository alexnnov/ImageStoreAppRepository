package com.imagestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagestore.domain.User;
import com.imagestore.domain.security.PasswordResetToken;
import com.imagestore.repository.PasswordResetTokenRepository;
import com.imagestore.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}
	
	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token,user);
		passwordResetTokenRepository.save(myToken);
	}
	

}
