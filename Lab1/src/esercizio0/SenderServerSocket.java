package esercizio0;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SenderServerSocket {
	
	final static String message = "This is a not so short text to test the reading capabilities of clients.";
	
	@SuppressWarnings("resource")
	public static void main (String[] args) {
		try {			
			Socket clientSocket;
			ServerSocket listenSocket;		
		
			listenSocket = new ServerSocket(53535);
			
			System.out.println("Running server : " +
					"Host=" + listenSocket.getInetAddress() +
					" Port=" + listenSocket.getLocalPort());
			
			while (true) {
				clientSocket = listenSocket.accept(); // qui attende il client. questo metodo è bloccante.
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				System.out.println(message);
				System.out.println("message length: " + message.length());
				out.write(message);
				out.flush();
				clientSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
