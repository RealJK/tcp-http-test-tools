import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Random;

public class FileNotFoundRequesterWorker extends Thread {

	private String hostname;
	private int port;
	private String text;
	private Random random;
	private int textMaxLength;
	
	public FileNotFoundRequesterWorker(String hostname, int port, String text) {
		this.hostname = hostname;
		this.port = port;
		this.text = text;
		this.textMaxLength = text.length();
		random = new Random();
	}

	@Override
	public void run() {

		Socket socket = null;
		PrintWriter out = null;

		while (true) {
			try {
				socket = new Socket(hostname, port);
				System.out.println("Connected");
	
				out = new PrintWriter(socket.getOutputStream(), true);
				
				int start = random.nextInt(textMaxLength - 500);
				int end = start + random.nextInt(50);
	
				String toSend = URLEncoder.encode(text.substring(start, end), "UTF-8");
				out.write("GET /" + toSend + " HTTP/1.1\n");
				out.write("Host: " + hostname);
				out.write("\r\n\r\n");
				out.flush();
				
				System.out.println("Sending unknown file request to socket");
	
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					socket.close();
					socket = null;
					System.out.println("Disconnected");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}

			try { Thread.sleep(100); } catch (Exception ex) {};
		}
	}
}
