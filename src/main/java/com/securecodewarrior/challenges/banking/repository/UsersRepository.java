package com.securecodewarrior.challenges.banking.repository;


import java.util.Date;

import com.securecodewarrior.challenges.banking.model.User;

/**
 *
 * AuthFailureRepository interface provides method for following:
 *
 * 1. check user exist or not in database
 * 2. update lockeuntil field in user table when three consecutive wrong login attempts
 * @author kushal shah
 *
 */
public interface UsersRepository {

	/**
	 * Retrieve a user for a given username.
	 *
	 * @param user
	 * @return
	 */
	public User checkUserNameExistsOrNot(String user);

	/**
	 *
	 * Update user table if user attempts three consecutive failure &
	 * it will prevent brute-force attack
	 *
	 * @param userName
	 * @param date
	 */
	public void lockUser(String userName, Date date);
}
