import java.net.Socket;


public class ConnectionTickler {

	public static void main(String[] args) {

		if (args.length != 3) {
			System.err.println("Usage: java ConnectionTickler [hostname] [port] [sleep]");
			System.exit(-1);
		}

		String hostname = args[0];

		// Port to connect to
		int port = -1;
		
		// Sleep for 100 milliseconds
		int sleep = 100;

		try {
			port = Integer.parseInt(args[1]);
		} catch (Exception ex) {
			System.err.println("Unable to parse port number");
			System.exit(-1);
		}

		try {
			sleep = Integer.parseInt(args[2]);
		} catch (Exception ex) {
			System.err.println("Unable to parse sleep time");
			System.exit(-1);
		}

		try {
			
			System.out.println("Connecting and disconnecting from socket every " + sleep + "ms.");

			while (true) {
				System.out.println("Connecting...");
				Socket socket = new Socket(hostname, port);
				socket.close();				
				System.out.println("Disconnected...");
				Thread.sleep(sleep);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}		
	}
}
