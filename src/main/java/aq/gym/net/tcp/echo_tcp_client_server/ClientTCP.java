package aq.gym.net.tcp.echo_tcp_client_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTCP {

	public static void main(String[] args) throws UnknownHostException {
		int port = 5082;
		InetAddress address = InetAddress.getByName("localhost");		
		try(Socket socket = new Socket(address, port);
				Scanner scanner = new Scanner(System.in)) {			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			System.out.println("Write a line...");
			while(true) {
				String line = scanner.nextLine();
				if(line.equals("end"))
					break;
				dos.writeUTF(line);
				String response = dis.readUTF();
				System.out.println(response);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
