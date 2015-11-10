package com.securecodewarrior.challenges.banking.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.securecodewarrior.challenges.banking.model.AuthFailure;
import com.securecodewarrior.challenges.banking.util.ConnectionFactory;
import com.securecodewarrior.challenges.banking.util.Log;
/**
 * This Class provides implementation methods for following:
 *
 * 1. insert failure entry in auth_failure table
 * 2. count how much times user attempts in 20 minutes and return list of AuthFailure
 *
 * @author kushal shah
 *
 */
public class AuthFailureRepositoryImpl implements AuthFailureRepository{
	private StringBuilder sql_query=null;


	/* (non-Javadoc)
	 * @see com.securecodewarrior.challenges.banking.repository.AuthFailureRepository#findByUsername(java.lang.String, java.util.Date)
	 */
	public List<AuthFailure> findByUsername(final String username, final Date date){

		sql_query=new StringBuilder();
		sql_query.append("SELECT * FROM AUTH_FAILURE WHERE USER_ID = ? AND TIMESTAMP >= ?");
		final List<AuthFailure> authFailures = new ArrayList<AuthFailure>();
		Connection connection;
		AuthFailure authFailure = null;

		try{
			connection = ConnectionFactory.getConnection();

			final PreparedStatement preparedStatement = connection.prepareCall(sql_query.toString());
			preparedStatement.setString(1, username);
			preparedStatement.setTimestamp(2, new Timestamp(date.getTime()));

			final ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){

				authFailure = new AuthFailure();
				authFailure.setUser_id(resultSet.getString("USER_ID"));
				authFailure.setTimestamp(resultSet.getTimestamp("TIMESTAMP"));
				authFailures.add(authFailure);

			}

		}
		catch (final SQLException e){
			Log.error(this.getClass().getName()+ "==> Error :"+e.getMessage());
		}
		return authFailures;

	}

	/* (non-Javadoc)
	 * @see com.securecodewarrior.challenges.banking.repository.AuthFailureRepository#save(java.lang.String)
	 */
	public void save(final String username){

		sql_query=new StringBuilder();
		sql_query.append("INSERT INTO AUTH_FAILURE(USER_ID,TIMESTAMP) VALUES (?,?)");
		PreparedStatement prep = null;

		try {
			final Connection connection = ConnectionFactory.getConnection();

			prep = connection.prepareCall(sql_query.toString());
			prep.clearParameters();
			prep.setString(1, username);
			prep.setTimestamp(2, new Timestamp(new Date().getTime()));

			prep.executeUpdate();

			}
		catch (final SQLException e){

			Log.error(this.getClass().getName()+ "==> Error :"+e.getMessage());

		}
	}
}
