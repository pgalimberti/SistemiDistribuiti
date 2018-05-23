package esercizio3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class ReceiverServer_3 {

	private static void acceptClientRequest(Selector selector, ServerSocketChannel serverSocket) throws IOException {
		SocketChannel client = serverSocket.accept();
		client.configureBlocking(false); // set the channel in non blocking mode
		// register the channel with the selector for the read operation
		client.register(selector, SelectionKey.OP_READ);
	}

	private static void readClientBytes(SelectionKey key) throws CharacterCodingException {
		SocketChannel client = (SocketChannel) key.channel();
		int BUFFER_SIZE = 256;
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE); // buffer to read and write
		// read bytes coming from the client
		try {
			if (client.read(buffer) == -1) {
				client.close();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Show bytes on the console using the port number to discriminate senders
		buffer.flip(); // set the limit to the current position and then set the position to zero
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer charBuffer = decoder.decode(buffer);
		System.out.println(client.socket().getPort() + ": " + charBuffer.toString());
	}

	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		// selectable and good for a stream-oriented listening socket
		ServerSocketChannel serverSocket = ServerSocketChannel.open();
		serverSocket.bind(new InetSocketAddress("localhost", 53535));
		serverSocket.configureBlocking(false); // set the channel in non blocking mode
		// register the channel with the selector or the accept operation
		serverSocket.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			selector.select();
			// each key refers to registered channels ready for an operation
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			// create an itarator for the ready channels
			Iterator<SelectionKey> iter = selectedKeys.iterator();
			while (iter.hasNext()) {
				SelectionKey key = iter.next();
				if (key.isAcceptable()) { // accept and set a connection with a clinet
					acceptClientRequest(selector, serverSocket);
				}
				if (key.isReadable()) { // serve a connected client
					readClientBytes(key);
				}
				iter.remove();
			}
		}
	}

}
