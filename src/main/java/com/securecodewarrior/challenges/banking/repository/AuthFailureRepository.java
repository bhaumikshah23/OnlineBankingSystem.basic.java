package com.securecodewarrior.challenges.banking.repository;

import java.util.Date;
import java.util.List;

import com.securecodewarrior.challenges.banking.model.AuthFailure;

/**
 *
 * Repository for LoginFailure entity.
 * AuthFailureRepository interface provides method for following:
 *
 * 1. insert failure entry in auth_failure table
 * 2. count how much times user attempts in 20 minutes and return list of AuthFailure
 *
 * @author kushal shah
 *
 */
public interface AuthFailureRepository {

	/**
	 * Save a failed login attempt for a given username.
	 * @param username
	 */
	public void save(String username);

	/**
	 *
	 * Retrieve the LoginFailures logged for a given username
	 * within 20 minutes from now.
	 *
	 * @param username
	 * @param date
	 * @return
	 */
	public List<AuthFailure> findByUsername(String username,Date date);

}
