package Serializzazione;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
	private Socket socket;

	private Client(InetAddress serverAddress, int serverPort) throws Exception {
		this.socket = new Socket(serverAddress, serverPort);
	}

	private void start() throws IOException, InterruptedException {
		
		Person persona = new Person("mario","rossi",34);

		ObjectOutputStream  outobj = new ObjectOutputStream(this.socket.getOutputStream());

		outobj.writeObject(persona);
		outobj.flush();
		outobj.close();

	}

	public static void main(String[] args) throws Exception {
		try {
			InetAddress serverAddress = InetAddress.getByName(args[0]); //host 0.0.0.0
			Client client = new Client(serverAddress, Integer.parseInt(args[1])); //port 21

			System.out.println("Connected to: " + client.socket.getInetAddress());
			client.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
