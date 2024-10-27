package simple_vpn;

import java.io.*;
import java.net.*;

import javax.crypto.SecretKey;

import encryption.EncryptionGen;

public class Client {
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int PORT = 8888;

	public static void main(String[] args) throws Exception {
		SecretKey key = EncryptionGen.generateKey();
		Socket socket = new Socket(SERVER_ADDRESS, PORT);
		System.out.println("Connected to VPN server");
		handleServerMessages(socket, key);
	}

	private static void handleServerMessages(Socket socket, SecretKey key) {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
			String userMessage;
			while((userMessage = userInput.readLine()) != null){
				byte[] encryptedmessage = EncryptionGen.encrypt(userMessage, key);
				out.println(userMessage);
				String response = in.readLine();
				System.out.println(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
