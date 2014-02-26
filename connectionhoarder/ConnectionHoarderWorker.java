import java.io.IOException;
import java.net.Socket;


public class ConnectionHoarderWorker extends Thread {

	private String hostname;
	private int port;
	private int timeToHoard;
	
	public ConnectionHoarderWorker(String hostname, int port, int timeToHoard) {
		this.hostname = hostname;
		this.port = port;
		this.timeToHoard = timeToHoard;
	}

	@Override
	public void run() {

		Socket socket = null;

		while (true) {
			try {
				socket = new Socket(hostname, port);
				System.out.println("Connected");
				Thread.sleep(timeToHoard);
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
		}
	}
}
