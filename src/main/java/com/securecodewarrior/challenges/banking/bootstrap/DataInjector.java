package com.securecodewarrior.challenges.banking.bootstrap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.securecodewarrior.challenges.banking.util.AppUtils;
import com.securecodewarrior.challenges.banking.util.ConnectionFactory;
import com.securecodewarrior.challenges.banking.util.Constants;
import com.securecodewarrior.challenges.banking.util.Log;

/**
 *
 * This class will responsible for create,insert and drop tables in HSQLDB(In memory database)
 *
 * As of now for insert purpose it will only insert one dummy user in HSQLDB(In memory database)
 *
 * @author kushal shah
 *
 */
public class DataInjector {

	/**
	 * This method will create dummy user in user table for login purpose in HSQLDB (In memory database)
	 *
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastname
	 * @param securityAnswer
	 */
	public void createUser(final String username, final String password,final String firstName, final String lastname,final String securityAnswer,final int role_id){

		Log.info(this.getClass().getName()+ "==> Method : createUser ==> Enter");

		final StringBuilder create_new_user=new StringBuilder();

		create_new_user.append("INSERT INTO USER(USERNAME,PASSWORD,FIRST_NAME,");
		create_new_user.append("LAST_NAME, EXPIRYDATE,ENABLED,CREDENTIALSEXPIRED");
		create_new_user.append(",SECURITYQUESTION,SECURITYANSWER,ROLE_ID ) VALUES");
		create_new_user.append("(?,?,?,?,?,?,?,?,?,?)");

		PreparedStatement prep = null;

		try{
			final Connection connection = ConnectionFactory.getConnection();
			prep = connection.prepareCall(create_new_user.toString());

			prep.clearParameters();
			prep.setString(1, username);
			prep.setString(2, AppUtils.generatePassword(password));
			prep.setString(3, firstName);
			prep.setString(4, lastname);
			prep.setBoolean(5, false);
			prep.setBoolean(6, true);
			prep.setBoolean(7, true);
			prep.setString(8, Constants.MSG_SECURITY_QUESTION);
			prep.setString(9, securityAnswer);
			prep.setInt(10, role_id );

			prep.executeUpdate();

			Log.info("Dummy user created");

		}
		catch (final SQLException e){
			e.printStackTrace();
			Log.error("Error : "+e.getMessage());
		}

		Log.info(this.getClass().getName()+ "==> Method : createUser ==> Exit");

	}

	/**
	 *
	 * This method will create all the necessary tables  in HSQLDB (In memory database)
	 *
	 * 1. User Table
	 * 2. Auth_Failure Table
	 *
	 */
	public void createTables(){

		Log.info(this.getClass().getName()+ "==> Method : createTables ==> Enter");

		final StringBuilder create_user_table=new StringBuilder();

		create_user_table.append("CREATE TABLE IF NOT EXISTS USER( ID INTEGER IDENTITY PRIMARY KEY,");
		create_user_table.append("USERNAME VARCHAR(255) NOT NULL , PASSWORD VARCHAR(255) NOT NULL ,");
		create_user_table.append("FIRST_NAME VARCHAR(255) NOT NULL , LAST_NAME VARCHAR(255) NOT NULL,");
		create_user_table.append("DISPLAY_NAME VARCHAR(255) , EXPIRYDATE BOOLEAN ,ENABLED BOOLEAN ,LOCKEDUNTIL TIMESTAMP,");
		create_user_table.append("CREDENTIALSEXPIRED BOOLEAN , SECURITYQUESTION VARCHAR(255) ,SECURITYANSWER VARCHAR(255),ROLE_ID INTEGER )");

		final StringBuilder create_authorityfailure_table=new StringBuilder();
		create_authorityfailure_table.append("CREATE TABLE IF NOT EXISTS AUTH_FAILURE (");
		create_authorityfailure_table.append("ID INTEGER IDENTITY PRIMARY KEY,");
		create_authorityfailure_table.append("USER_ID VARCHAR(255) NOT NULL,TIMESTAMP TIMESTAMP)");

		try{
			final Connection connection = ConnectionFactory.getConnection();
			final Statement statement = connection.createStatement();
			statement.execute(create_user_table.toString());
			statement.execute(create_authorityfailure_table.toString());
			Log.info("CREATE USER TABLE");
		}
		catch (final SQLException e){
			e.printStackTrace();
			Log.error("Error : "+e.getMessage());
		}

		Log.info(this.getClass().getName()+ "==> Method : createUser ==> Exit");

	}

	/**
	 *
	 * This method will drop the tables in HSQLDB(In memory database)
	 *
	 * 1. User Table
	 * 2. Auth_Failure Table
	 *
	 */
	public void droptables(){

		final StringBuilder drop_user_table=new StringBuilder();
		drop_user_table.append("DROP TABLE USER IF EXISTS");

		final StringBuilder drop_auth_table=new StringBuilder();
		drop_auth_table.append("DROP TABLE AUTH_FAILURE IF EXISTS");

		try{
			final Connection connection = ConnectionFactory.getConnection();
			final Statement statement = connection.createStatement();
			statement.execute(drop_user_table.toString());
			statement.execute(drop_auth_table.toString());
		}
		catch (final SQLException e){
			Log.error("Error : "+e.getMessage());
		}
	}

}
