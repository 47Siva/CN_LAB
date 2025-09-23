import java.io.*;
import java.net.*;

public class Server {
    public static final int PORT = 4000;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server Started on port " + PORT);

            try {
                // Wait for client connection
                socket = serverSocket.accept();
                System.out.println("Client Connected: " + socket);

                // Read message from client
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Client says: " + reader.readLine());

                // Send response to client
                PrintStream outputStream = new PrintStream(socket.getOutputStream());
                outputStream.println("Hello from server");

                outputStream.close();
                socket.close();
            } catch (SocketException se) {
                System.out.println("Server socket problem: " + se.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Couldn't start server: " + e.getMessage());
        }

        if (socket != null) {
            System.out.println("Connection from: " + socket.getInetAddress());
        }
    }
}