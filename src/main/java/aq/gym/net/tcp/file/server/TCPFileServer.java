package aq.gym.net.tcp.file.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.ConsoleHandler;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class TCPFileServer {

	private static final int PORT = 5082;
	private ServerSocket serverSocket;
	private Logger logger;
	private ConsoleHandler errorConsoleHandler;
	private ConsoleHandler infoConsoleHandler;
	
	public TCPFileServer() {
		initLogger();
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initLogger() {
		errorConsoleHandler = new ConsoleHandler();
		errorConsoleHandler.setLevel(Level.WARNING);
		infoConsoleHandler = new ConsoleHandler() {
			@Override
			protected synchronized void setOutputStream(OutputStream out) throws SecurityException {
				super.setOutputStream(System.out);
			}
		};
		infoConsoleHandler.setFormatter(new Formatter() {
			
			@Override
			public String format(LogRecord record) {
				return record.getMessage() + "\n";
			}
		});
		infoConsoleHandler.setFilter(new Filter() {
			
			@Override
			public boolean isLoggable(LogRecord record) {
				return record.getLevel().intValue() <= Level.INFO.intValue() ? true : false;
			}
		});
		infoConsoleHandler.setLevel(Level.INFO);
		logger = Logger.getLogger("com.other.network.tcp.file.server");
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.INFO);
		logger.addHandler(infoConsoleHandler);
		logger.addHandler(errorConsoleHandler);
	}
	
	public void receiveFile() {
		logger.info("[Server started]");
		try {
			Socket clientSocket = serverSocket.accept();
			logger.info("[Server accepted a client]");
			Runnable run = () -> {
				logger.info("[Server handle accepted client in " + Thread.currentThread().getName() + " ]");
				try(BufferedInputStream bis = new BufferedInputStream(clientSocket.getInputStream());
						FileOutputStream fos = new FileOutputStream(new File("src/main/java/aq/gym/net/tcp/file/server/norwayReceive.jpg"))) {
					byte[] buff = new byte[256];
					while(bis.read(buff) != -1) {
						fos.write(buff);
						fos.flush();
					}
				} catch (IOException e) {
					logger.log(Level.WARNING, "[Client disconnected]");
				}
				logger.info("[Process of receive a client file success]");
			};
			Thread clientHandlerThread = new Thread(run);
			clientHandlerThread.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
