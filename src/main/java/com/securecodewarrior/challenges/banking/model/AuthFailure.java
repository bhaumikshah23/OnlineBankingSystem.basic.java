package com.securecodewarrior.challenges.banking.model;

import java.util.Date;

/**
 * AuthFailure represents auth_failure table
 * @author kushal shah
 *
 */
public class AuthFailure {

	private Long id;
	private String user_id;
	private Date timestamp;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(final String user_id) {
		this.user_id = user_id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

}
