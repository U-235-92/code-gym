package aq.gym.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WhereSaveLogFileByDeafult {

	private static final Logger LOG = Logger.getLogger(WhereSaveLogFileByDeafult.class.getName());
	
	public static void main(String[] args) throws IOException {
		FileHandler fHandler = new FileHandler("mylog.txt"); //By default it saves file in root directory of project
		fHandler.setLevel(Level.INFO);
		LOG.addHandler(fHandler);
		LOG.setLevel(Level.INFO);
		LOG.info("Log message, level: INFO");
	}
}
