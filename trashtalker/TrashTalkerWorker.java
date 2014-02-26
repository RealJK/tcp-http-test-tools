import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class TrashTalkerWorker extends Thread {

	private String hostname;
	private int port;
	private String text;
	private Random random;
	private int textMaxLength;
	
	public TrashTalkerWorker(String hostname, int port, String text) {
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
				int end = start + random.nextInt(500);
	
				String toSend = text.substring(start, end);
				out.write(toSend);
				out.write("\r\n\r\n");
				out.flush();
				
				System.out.println("Sending crap to socket");
	
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

			try { Thread.sleep(50); } catch (Exception ex) {};
		}
	}
}
