import java.io.*;
import java.net.*;

public class FTServer {
    public static void main(String[] args) throws IOException {
        try {
            // Start server on port 4000
            ServerSocket serverSocket = new ServerSocket(4000);
            System.out.println("Server started. Waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Accepted connection: " + socket);

            // File to send
            File transferFile = new File("send.txt");
            byte[] bytearray = new byte[(int) transferFile.length()];

            FileInputStream fin = new FileInputStream(transferFile);
            BufferedInputStream bin = new BufferedInputStream(fin);
            bin.read(bytearray, 0, bytearray.length);

            // Send file to client
            OutputStream os = socket.getOutputStream();
            System.out.println("Sending file...");
            os.write(bytearray, 0, bytearray.length);
            os.flush();

            System.out.println("File transfer complete");

            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
