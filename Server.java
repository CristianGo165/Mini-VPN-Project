package simple_vpn;
import java.io.*;
import java.net.*;

public class Server {
	private static final int PORTNUM = 8888;
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORTNUM);
		System.out.println("VPN running on port: " + PORTNUM);
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("Client Connected");
			handleClient(socket);
		}
	}
	
	public static void handleClient(Socket socket) {
		try(
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true)	
				){
			String message;
			while((message = in.readLine()) != null){
				System.out.println("Message from client: " + message);
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
