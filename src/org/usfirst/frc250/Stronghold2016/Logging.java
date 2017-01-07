package org.usfirst.frc250.Stronghold2016;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
	private static Logger logger = Logger.getLogger(Logging.class.getName());
	private static FileHandler localHandler;
	private static FileHandler usbHandler;
	private static ConsoleHandler consoleHandler = new ConsoleHandler();
	private static SimpleFormatter formatter = new SimpleFormatter();

	public static void init () {
		try {
			localHandler = new FileHandler("/home/lvuser/logs/" + new Date().toString() + ".log");
			localHandler.setFormatter(formatter);
			Logger.getLogger("").addHandler(localHandler);
		} catch (SecurityException | IOException e) {
//			logger.warning("File logging failed.");
//			logger.fine(e.getMessage());
		}
		try {
			File file = new File("/media/sda1/logs/" + new Date().toString().replace(":", "_") + ".log");
			usbHandler = new FileHandler(file.getAbsolutePath());
			usbHandler.setFormatter(formatter);
			Logger.getLogger("").addHandler(usbHandler);
		} catch (SecurityException | IOException e) {
//			logger.warning("USB logging failed.");
//			logger.info(e.getMessage());	
		}
		consoleHandler.setFormatter(formatter);
		Logger.getLogger("").addHandler(consoleHandler);
	}
}
