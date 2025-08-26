package aq.gym.logging;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class FormatLogMessages {

	private static final Logger LOG = Logger.getLogger(WhereSaveLogFileByDeafult.class.getName());
	
	public static void main(String[] args) throws IOException {
		Formatter formatter = new Formatter() {
			@Override
			public String format(LogRecord record) {
				LocalDateTime recordLdt = LocalDateTime.ofInstant(Instant.ofEpochMilli(record.getMillis()), ZoneId.systemDefault());
				String recordDate = recordLdt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
				String recordClass = record.getSourceClassName();
				String recordMethod = record.getSourceMethodName();
				String recordMessage = record.getMessage();
				String recordLevel = record.getLevel().getName();
				return String.format("[%s] [%s: %s] %s: %s%n", recordDate, recordClass, recordMethod, recordLevel, recordMessage);
			}
		};
		Handler consoleHandler = new ConsoleHandler();
		Handler fileHandler = new FileHandler("formatted-log.log", true);
		consoleHandler.setFormatter(formatter);
		fileHandler.setFormatter(formatter);
		LOG.setUseParentHandlers(false);
		LOG.addHandler(consoleHandler);
		LOG.addHandler(fileHandler);
		LOG.setLevel(Level.INFO);
		LOG.info("Welcome to custom log-file!");
	}
}
