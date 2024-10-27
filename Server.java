package simple_vpn;
import java.io.*;
import java.net.*;

import javax.crypto.SecretKey;

import encryption.EncryptionGen;

public class Server {
	private static final int PORTNUM = 8888;
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(PORTNUM);
		SecretKey key = EncryptionGen.generateKey();
		System.out.println("VPN running on port: " + PORTNUM);
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("Client Connected");
			handleClient(socket, key);
		}
	}
	
	public static void handleClient(Socket socket, SecretKey key) {
		try(
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true)	
				){
			String message;
			while((message = in.readLine()) != null){
				String decryptedMessage = EncryptionGen.decrypt(message.getBytes(), key);
				System.out.println("Message from client: " + message);
				byte[] encryptedResponse = EncryptionGen.encrypt("Echo: " + decryptedMessage, key);
				out.println(new String(encryptedResponse));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
