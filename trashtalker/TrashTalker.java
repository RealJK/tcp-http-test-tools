import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class TrashTalker {

	public static void main(String[] args) {
		
		if (args.length != 4) {
			System.err.println("Usage: java TrashTalker [hostname] [port] [number of threads] [text file]");
			System.exit(-1);
		}
		
		String hostname = args[0];
		int port = -1;
		int numberOfThreads = 8;
		String filename = args[3];

		try {
			port = Integer.parseInt(args[1]);
			numberOfThreads = Integer.parseInt(args[2]);
		} catch (Exception ex) {
			System.err.println("Unable to parse arguments.");
			System.exit(-1);
		}

		File textFile = new File(filename);
		
		if (!textFile.exists()) {
			System.err.println("Unable to find file " + textFile);
			System.exit(-1);
		}

	    StringBuilder contents = new StringBuilder();
	    
		try {

			BufferedReader input = new BufferedReader(new FileReader(textFile));
			try {
				String line = null;

				while ((line = input.readLine()) != null) {
					contents.append(line);
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		System.out.println("Creating " + numberOfThreads + " workers to trash talk " + hostname + ":" + port);

		for (int i=0; i<numberOfThreads; i++) {
			TrashTalkerWorker worker = new TrashTalkerWorker(hostname, port, contents.toString());
			worker.start();
		}

		while (true) {
			/* nop */
		}
	}
}
