package in.Chat;
import java.io.*;
import java.net.*;

public class ChatClient {
	
	private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        System.out.println("Connected to the server");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Read messages from server
        new Thread(() -> {
            String msg;
            try {
                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        String userMsg;
        while ((userMsg = consoleInput.readLine()) != null) {
            out.println(userMsg);
        }

        socket.close();
    }

}
