package esercizio2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SenderClient_2 {

	private Socket socket;
	private Scanner scanner;

	private SenderClient_2(InetAddress serverAddress, int serverPort) throws Exception { //inizializzo la connessione
		this.socket = new Socket(serverAddress, serverPort);
		this.scanner = new Scanner(System.in);
	}

	private void start() throws IOException, InterruptedException {
		String input;
		PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
				
		while (true) {
			System.out.println("Inserire il messaggio da mandare al server : ");
			input = scanner.nextLine();
			if (input.contentEquals("exit"))
				break;
			out.print(input);
			out.flush();
		}
		
		System.out.println("Client terminate.");
		socket.close();
	}

	public static void main(String[] args) throws Exception {
		SenderClient_2 client = new SenderClient_2(InetAddress.getByName(args[0]), Integer.parseInt(args[1])); //stabilisco la connessione
		System.out.println("Connected to: " + client.socket.getInetAddress());
		client.start(); 
	}

}
