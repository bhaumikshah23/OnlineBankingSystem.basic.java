package com.securecodewarrior.challenges.banking.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import com.securecodewarrior.challenges.banking.model.User;
import com.securecodewarrior.challenges.banking.repository.UsersRepostitoryImpl;
import com.securecodewarrior.challenges.banking.util.AppUtils;
import com.securecodewarrior.challenges.banking.util.Constants;
import com.securecodewarrior.challenges.banking.util.Log;

/**
 * This class will provide authentication
 *
 * 1. Check username is valid or not
 * 2. Check user is locked or not
 * 3. Check user password is valid or not
 *
 * @author kushal shah
 *
 */
public class UsersService {

	/**
	 *
	 * This method will check user is valid or not
	 *
	 * @param user
	 * @return
	 */
public HashMap<String,Object> validLogin(final User user){
	Log.info(this.getClass().getName()+ "==> Method : validLogin ==> Enter");

	HashMap<String, Object> resultHashMap=new HashMap<String, Object>();
	final UsersRepostitoryImpl usersRepostitoryImpl=new UsersRepostitoryImpl();
	final AuthFailureService authFailureService=new AuthFailureService();

		if(!authFailureService.checkLockedUsername(user.getUsername())){
			final User checkUser=usersRepostitoryImpl.checkUserNameExistsOrNot(user.getUsername());
			if(checkUser!=null){
					if(checkUser.getLocked()!=null){
						if(isUserLocked(checkUser.getLocked())){
							resultHashMap=checkForValidPassword(user, checkUser);
						}
						else{
							resultHashMap.put("isError", 1);
							resultHashMap.put("errormessage", Constants.MSG_ACCOUNT_LOCKED);
						}
					}
					else{
						resultHashMap=checkForValidPassword(user, checkUser);
					}
				}
			else{
				authFailureService.authFailure(user.getUsername());
				resultHashMap.put("isError", 1);
				resultHashMap.put("errormessage", Constants.MSG_REQUIRED_USERNAME_PASSWORD);
			}
	}
	else{
		resultHashMap.put("isError", 1);
		resultHashMap.put("errormessage", Constants.MSG_ACCOUNT_LOCKED);
	}

	Log.info(this.getClass().getName()+ "==> Method : validLogin ==> Exit");

	return resultHashMap;
}

/**
 * This method will check user is locked or not
 *
 * @param date
 * @return
 */
private boolean isUserLocked(final Date date){
	boolean isNonLocked=false;
	final Timestamp currentTimestamp=new Timestamp(new Date().getTime());
	if(currentTimestamp.after(date)){
		isNonLocked=true;
	}
	return isNonLocked;
}
/**
 * This method will check password is valid or not
 *
 * @param user
 * @param checkUser
 * @return
 */
private HashMap<String, Object> checkForValidPassword(final User user,final User checkUser){

	final User successUser=new User();
	final HashMap<String, Object> resultHashMap=new HashMap<String, Object>();
	final AuthFailureService authFailureService=new AuthFailureService();

	if(AppUtils.isValidPassword(user.getPassword(), checkUser.getPassword())){
		successUser.setFirstName(checkUser.getFirstName());
		successUser.setLastName(checkUser.getLastName());
		successUser.setUsername(checkUser.getUsername());
		successUser.setRole_id(checkUser.getRole_id());
		resultHashMap.put("successUser", successUser);
		resultHashMap.put("isError", 0);
	}
	else{
		authFailureService.authFailure(checkUser.getUsername());
		resultHashMap.put("isError", 1);
		resultHashMap.put("errormessage", Constants.MSG_BAD_LOGIN_INPUT);
	}
	return resultHashMap;
}
}
