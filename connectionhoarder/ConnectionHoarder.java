
public class ConnectionHoarder {

	public static void main(String[] args) {

		if (args.length != 4) {
			System.err.println("Usage: java ConnectionHoarder [hostname] [port] [numberOfThreads] [timeToLeaveConnectionOpen]");
			System.exit(-1);
		}

		String hostname = args[0];
		int port = -1;		
		int numberOfThreads = 100;
		int timeToLeaveConnectionOpen = 2500;	// 2500ms

		try {
			port = Integer.parseInt(args[1]);
			numberOfThreads = Integer.parseInt(args[2]);
			timeToLeaveConnectionOpen = Integer.parseInt(args[3]);
		} catch (Exception ex) {
			System.err.println("Unable to parse arguments.");
			System.exit(-1);
		}

		try {
			System.out.println("Creating " + numberOfThreads + " threads, and leaving connection open for " + timeToLeaveConnectionOpen + " ms.");

			for (int i=0; i< numberOfThreads; i++) {
				ConnectionHoarderWorker worker = new ConnectionHoarderWorker(hostname, port, timeToLeaveConnectionOpen);
				worker.start();				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}		

		while (true) {
			/* nop */
		}
	}
}
