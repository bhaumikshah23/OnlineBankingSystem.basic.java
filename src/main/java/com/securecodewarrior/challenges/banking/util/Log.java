package com.securecodewarrior.challenges.banking.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author kushal shah
 *
 */
public class Log {
	static final Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static void info(final String message){
		Log.logger.setLevel(Level.FINER);
		Log.logger.info(message);
	}
	public static void error(final String message){
		Log.logger.setLevel(Level.WARNING);
		Log.logger.info(message);
	}
}
