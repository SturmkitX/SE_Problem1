import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerReaderThread extends Thread {

	private EchoServer server;

	public ServerReaderThread(EchoServer server) {
		this.server = server;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while(true) {
				String message = reader.readLine();
        String[] comms = message.split(" ");
        switch(comms[0]) {
          case "#quit" : server.stopListening(); server.close(); System.exit(0);
          case "#stop" : server.stopListening(); break;
          case "#close" : server.stopListening(); server.close(); break;
          case "#setport" : if(server.isListening()) {
            System.out.println("Server is already running!");
          } else {
            server.setPort(Integer.parseInt(comms[1]));
          }; break;

          case "#start" : if(server.isListening()) {
            System.out.println("Server is already running!");
          } else {
            server.listen();
          }; break;

          case "#getport" : System.out.println("Port: " + server.getPort()); break;
          default : server.handleMessageFromServerUI("SERVER MSG>" + message);
        }

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
