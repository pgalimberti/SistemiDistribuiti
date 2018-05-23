package Serializzazione;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		boolean end = false;
		while (!end) {
			try {
				Socket clientSocket;
				ServerSocket listenSocket;
				
				listenSocket = new ServerSocket(53535);
				
				System.out.println("\nRunning Server: " + "Host=" + listenSocket.getLocalSocketAddress() + " Port="
						+ listenSocket.getLocalPort());
	
				clientSocket = listenSocket.accept();
				
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
	
				System.out.println("Ready to read from the socket");
				
				Person persona = (Person)in.readObject();				
				System.out.println("Nome : " + persona.getNome());
				System.out.println("Cognome : " + persona.getCognome());
				System.out.println("Età : " + persona.getEta());
				
				
				FileOutputStream fos = new FileOutputStream("src/output/output_server.txt");;
				ObjectOutputStream outTxt = new ObjectOutputStream(fos);
				outTxt.writeObject(persona);
				outTxt.close();				
				
				System.out.println("____");
					
				clientSocket.close();
				listenSocket.close();
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
