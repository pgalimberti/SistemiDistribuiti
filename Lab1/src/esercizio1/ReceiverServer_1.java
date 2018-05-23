package esercizio1;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverServer_1 {

	public static void main(String[] args) {
		ServerSocket listenSocket;
		Socket clientSocket;
		byte[] byteReceived = new byte[1000];
		String messageString = "";
		try {
			listenSocket = new ServerSocket(53535); // crea una socket di ascolto sulla porta 53535
			
			System.out.println("Running server : " +
					"Host=" + listenSocket.getInetAddress() +
					" Port=" + listenSocket.getLocalPort());
			
			while (true) { // listen, serve, close, next-client loop
				clientSocket = listenSocket.accept(); // attende una socket del client
				DataInputStream in = new DataInputStream(clientSocket.getInputStream()); // canale di connessione per la lettura dello stream di byte
				int bytesRead = 0;
				while (true) { // loop to read all the bytes sent by the current client
					bytesRead = in.read(byteReceived);
					if (bytesRead == -1)
						break; // no more bytes
					messageString += new String(byteReceived, 0, bytesRead); // all the bytes received so far
					System.out.println("Received: " + messageString);
				}
				clientSocket.close();
				messageString = "";
				System.out.println("*** Avaialble to the next client.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
