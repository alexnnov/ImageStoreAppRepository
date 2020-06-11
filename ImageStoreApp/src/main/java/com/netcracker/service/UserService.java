package com.imagestore.service;

import com.imagestore.domain.User;
import com.imagestore.domain.security.PasswordResetToken;

public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user,final String token);

}
