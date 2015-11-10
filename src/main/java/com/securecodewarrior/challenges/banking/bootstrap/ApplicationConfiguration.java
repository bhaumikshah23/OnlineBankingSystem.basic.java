package com.securecodewarrior.challenges.banking.bootstrap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.securecodewarrior.challenges.banking.util.Constants;
import com.securecodewarrior.challenges.banking.util.Log;

/**
 * This configuration file loads by intialization of servlet context.
 *
 * 1. It will first drop the tables using @DataInjector in HSQLDB (In memory database)
 * 2. It will create all the necessary tables using @DataInjector in HSQLDB (In memory database)
 * 3. It will create dummy user in user table for login purpose in HSQLDB (In memory database)
 *
 * @author kushal shah
 *
 */
public class ApplicationConfiguration implements ServletContextListener{

	public void contextDestroyed(final ServletContextEvent arg0) {

	}

	public void contextInitialized(final ServletContextEvent arg0) {

		Log.info(this.getClass().getName()+ "==> Method : contextInitialized ==> Enter");
		Log.info("----------------------------------------------");
		Log.info("INITIALIZING SEED DATA INTO IN-MEMORY DATABASE");
		Log.info("----------------------------------------------");

		final DataInjector injector=new DataInjector();

		injector.droptables();
		injector.createTables();

		injector.createUser("john.miller", "john.password", "john", "miller", "william",Constants.CUSTOMER_ROLE_ID);
		injector.createUser("john.kale", "john.password", "john", "miller", "william",Constants.ADMIN_ROLE_ID);

		Log.info(this.getClass().getName()+ "==> Method : contextInitialized ==> Exit");

	}

}
