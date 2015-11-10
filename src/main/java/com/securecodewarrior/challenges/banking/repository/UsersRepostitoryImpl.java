package com.securecodewarrior.challenges.banking.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.securecodewarrior.challenges.banking.model.User;
import com.securecodewarrior.challenges.banking.util.ConnectionFactory;
import com.securecodewarrior.challenges.banking.util.Log;

/**
 * This Class provides implementation methods for following:
 *
 *	1. check user exist or not in database
 *  2. update lockeuntil field in user table when three consecutive wrong login attempts
 * @author kushal shah
 *
 */
public class UsersRepostitoryImpl implements UsersRepository{


	private StringBuilder sql_query=null;

	/* (non-Javadoc)
	 * @see com.securecodewarrior.challenges.banking.repository.UsersRepository#checkUserNameExistsOrNot(java.lang.String)
	 */
	public User checkUserNameExistsOrNot(final String user){

		sql_query=new StringBuilder();
		sql_query.append("SELECT LOCKEDUNTIL,FIRST_NAME,LAST_NAME,PASSWORD,USERNAME,ROLE_ID FROM USER WHERE USERNAME=?");
		Connection connection=null;
		User returnUser = null;

		try{
			connection = ConnectionFactory.getConnection();

			final PreparedStatement preparedStatement = connection.prepareCall(sql_query.toString());
			preparedStatement.setString(1, user);

			final ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()){

				returnUser = new User();
				returnUser.setUsername(resultSet.getString("USERNAME"));
				returnUser.setFirstName(resultSet.getString("FIRST_NAME"));
				returnUser.setLastName(resultSet.getString("LAST_NAME"));
				returnUser.setPassword(resultSet.getString("PASSWORD"));
				returnUser.setRole_id(resultSet.getInt("ROLE_ID"));
				if(resultSet.getTimestamp("LOCKEDUNTIL")!=null){

				returnUser.setLocked(resultSet.getTimestamp("LOCKEDUNTIL"));

				}
			}

		 }
		catch (final SQLException e){
			Log.error(this.getClass().getName()+ "==> Error :"+e.getMessage());
		}

		return returnUser;

	}

	/* (non-Javadoc)
	 * @see com.securecodewarrior.challenges.banking.repository.UsersRepository#lockUser(java.lang.String, java.util.Date)
	 */
	public void lockUser(final String userName, final Date date){

		sql_query=new StringBuilder();
		sql_query.append("UPDATE USER SET LOCKEDUNTIL=? WHERE USERNAME=?");
		Connection connection=null;
		try{
			connection = ConnectionFactory.getConnection();

			final PreparedStatement preparedStatement = connection.prepareCall(sql_query.toString());
			preparedStatement.setTimestamp(1, new Timestamp(date.getTime()));
			preparedStatement.setString(2, userName);

			preparedStatement.executeUpdate();

		}
		catch (final SQLException e){

			Log.error(this.getClass().getName()+ "==> Error :"+e.getMessage());

		}
	}
}
