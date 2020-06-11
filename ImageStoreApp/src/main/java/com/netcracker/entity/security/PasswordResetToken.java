package com.imagestore.domain.security;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.imagestore.domain.User;

import lombok.Data;

@Entity
@Data
public class PasswordResetToken {
	private static final int EXPIRATION = 60*24;
	
	@Id
	@GeneratedVaue(strategy = GeneratedType.AUTO)
	private Long id;
	
	private String token;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false, name="user.id")
	private User user;
	
	private Date expiryDate;
	
	public PasswordResetToken(final String token, final User user) {
		super();
		
		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}
	
	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}
	
	public void updateToken(final String token) {
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

}
