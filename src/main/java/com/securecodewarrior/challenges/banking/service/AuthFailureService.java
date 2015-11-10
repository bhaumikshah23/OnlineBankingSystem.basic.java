package com.securecodewarrior.challenges.banking.service;

import java.util.Calendar;
import java.util.Collection;

import com.securecodewarrior.challenges.banking.model.AuthFailure;
import com.securecodewarrior.challenges.banking.repository.AuthFailureRepositoryImpl;
import com.securecodewarrior.challenges.banking.repository.UsersRepostitoryImpl;
import com.securecodewarrior.challenges.banking.util.Constants;

/**
 *
 * This class will responsible when user enter bad credentials
 *
 * 1. lock user for 30 minutes when user attempts wrong 3 times in 20 minutes
 * 2. insert userdetail in authfailure table when user enter bad credentials
 *
 * @author kushal shah
 *
 */
public class AuthFailureService {

	/**
	 *  Verify if a user is locked out
	 *
	 * @param username
	 * @return
	 */
	public boolean checkLockedUsername(final String username){
		final UsersRepostitoryImpl usersRepostitoryImpl=new UsersRepostitoryImpl();
		final AuthFailureRepositoryImpl authFailureRepositoryImpl=new AuthFailureRepositoryImpl();
		boolean isLocked=false;
		final Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.MINUTE, -Constants.THRESHOLDMINUTES);

		final Collection<AuthFailure> failures = authFailureRepositoryImpl.findByUsername(username, calendar.getTime());

		if (failures.size() > Constants.THRESHOLDATTEMPTS){
				final Calendar lockUntil = Calendar.getInstance();
				lockUntil.add(Calendar.MINUTE, Constants.LOCKOUTMINUTES);
				usersRepostitoryImpl.lockUser(username, lockUntil.getTime());

				isLocked=true;

		}
		return isLocked;
	}
	/**
	 * insert userdetail in authfailure table when user enter bad credentials
	 *
	 * @param username
	 */
	public void authFailure(final String username){
		final AuthFailureRepositoryImpl authFailureRepositoryImpl=new AuthFailureRepositoryImpl();
		authFailureRepositoryImpl.save(username);
	}

}
