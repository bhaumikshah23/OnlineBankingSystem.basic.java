package com.securecodewarrior.challenges.banking.model;

import java.util.Date;

/**
 * User represents user table
 *
 * @author kushal shah
 *
 */
public class User
{

	private Long id;

	//CREDENTIALS

	private String username;
	private String password;

	//PERSONAL INFO

	private String firstName;
	private String lastName;
	private String displayName;

	//Security Info

	private boolean expiryDate;
	private Date lockeduntil;
	private boolean enabled;
	private boolean credentialsExpired;
	private String securityQuestion;
	private String securityAnswer;

	private int role_id;

	public Date getLockeduntil() {
		return lockeduntil;
	}
	public void setLockeduntil(final Date lockeduntil) {
		this.lockeduntil = lockeduntil;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(final int role_id) {
		this.role_id = role_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(final Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}
	public boolean isExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(final boolean expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getLocked() {
		return lockeduntil;
	}
	public void setLocked(final Date lockeduntil) {
		this.lockeduntil = lockeduntil;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(final boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(final String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(final String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}



}
