import common.ChatIF;

public class ServerConsole implements ChatIF {

	private static final int DEFAULT_PORT = 5555;
	private EchoServer server;
	private ServerReaderThread serverReader;

	public ServerConsole(int port) {
		this.server = new EchoServer(port, this);
		this.serverReader = new ServerReaderThread(server);
	}

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		System.out.println(">" + message);
	}

	public EchoServer getServerInstance() {
		return server;
	}

	public ServerReaderThread getReaderThread() {
		return serverReader;
	}

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0]
	 *            The port number to listen on. Defaults to 5555 if no argument is
	 *            entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		ServerConsole sv = new ServerConsole(port);
		sv.getReaderThread().start();

		try {
			sv.getServerInstance().listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
