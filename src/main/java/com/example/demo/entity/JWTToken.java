package com.example.demo.entity;

import java.time.LocalDateTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "jwt_tokens")
public class JWTToken {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Specifies that the tokenId will be auto-generated.
	@Column(name = "token_id", nullable = false) // Stores the unique identifier for each token.

	private Integer tokenId;
	
	@ManyToOne //Establishes a many-to-one relationship with the User entity
	@JoinColumn(name = "user_id",nullable=false) // Links the token to a specific user in the Users table
	private User user;// Represents the user associated with the token
	
	@Column(nullable=false) // Ensures that the token cannot be null
	private String token; // Stores the JWT token string
	
	@Column(nullable=false)// Ensures that the expiration time cannot be null.
	private LocalDateTime expiresAt; //Stores the expiration time of the token

	public JWTToken(Integer tokenId, User user, String token, LocalDateTime expiresAt) {
		super();
		this.tokenId = tokenId;
		this.user = user;
		this.token = token;
		this.expiresAt = expiresAt;
	}

	public JWTToken(User user, String token, LocalDateTime expiresAt) {
		super();
		this.user = user;
		this.token = token;
		this.expiresAt = expiresAt;
	}

	public Integer getTokenId() {
		return tokenId;
	}

	public void setToken_id(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public JWTToken() {
		
	}
	
}
