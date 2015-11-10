package com.securecodewarrior.challenges.banking.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hsqldb.jdbc.JDBCDataSource;

/**
 * Convenience "factory" class to facilitate connection to in memory database
 *
 * @author kushal shah
 *
 */
public class ConnectionFactory{
	private static Connection connection;

	private ConnectionFactory(){
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		if(ConnectionFactory.connection==null){
			final JDBCDataSource dataSource = new JDBCDataSource();
			dataSource.setDatabase("jdbc:hsqldb:file:onlinebankingdatabase");
			ConnectionFactory.connection=dataSource.getConnection("","");
		}
		return ConnectionFactory.connection;
	}

}
