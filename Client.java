package simple_vpn;

import java.io.*;
import java.net.*;

public class Client {
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int PORT = 8888;

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket(SERVER_ADDRESS, PORT);
		System.out.println("Connected to VPN server");
		handleServerMessages(socket);
	}

	private static void handleServerMessages(Socket socket) {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
			String userMessage;
			while((userMessage = userInput.readLine()) != null){
				out.println(userMessage);
				String response = in.readLine();
				System.out.println(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
