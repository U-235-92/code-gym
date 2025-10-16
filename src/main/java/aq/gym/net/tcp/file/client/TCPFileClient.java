package aq.gym.net.tcp.file.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class TCPFileClient {

	private static final int SERVER_PORT = 5082;
	private static final int CLIENT_PORT = 8250;
	
	private Socket socket;
	private InetAddress address;
	private Logger logger;
	private Handler consoleInfoHandler;
	private Handler consoleWarningHandler;
	private Formatter consoleInfoFormatter;
	
	public TCPFileClient() {
		initLogger();
		try {
			address = InetAddress.getLocalHost();
			socket = new Socket(address, SERVER_PORT, address, CLIENT_PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initLogger() {
		consoleInfoFormatter = new Formatter() {
			@Override
			public String format(LogRecord record) {
				return record.getMessage() + "\n";
			}
		};
		consoleInfoHandler = new ConsoleHandler() {
			@Override
			protected synchronized void setOutputStream(OutputStream out) throws SecurityException {
				super.setOutputStream(System.out);
			}
		};
		consoleInfoHandler.setLevel(Level.INFO);
		consoleInfoHandler.setFormatter(consoleInfoFormatter);
		consoleWarningHandler = new ConsoleHandler();
		consoleWarningHandler.setLevel(Level.WARNING);
		logger = Logger.getLogger("com.other.network.tcp.file.client");
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.INFO);
		logger.addHandler(consoleInfoHandler);
		logger.addHandler(consoleWarningHandler);
	}
	
	public void sendFile() {
		logger.info("[A client starts send a file]");
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("src/main/java/aq/gym/net/tcp/file/client/norway.jpg")))) {
			byte[] buff = new byte[256];
			while(bis.read(buff) != -1) {
				socket.getOutputStream().write(buff);
			}
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "File not found", e);
		} catch (IOException e) {
			logger.log(Level.WARNING, "Error during send a file", e.initCause(new ConnectException()));
		}
		logger.info("[A client finishes send a file]");
	}
}
