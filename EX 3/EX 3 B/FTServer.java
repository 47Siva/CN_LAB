import java.io.*;
import java.net.*;

public class FTServer {
    public static void main(String[] args) {
        final int PORT = 4000;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Accepted connection: " + socket);

            // File to send
            File transferFile = new File("send.txt");
            if (!transferFile.exists()) {
                System.out.println("Error: File not found -> " + transferFile.getAbsolutePath());
                socket.close();
                return;
            }

            byte[] bytearray = new byte[(int) transferFile.length()];

            try (FileInputStream fin = new FileInputStream(transferFile);
                 BufferedInputStream bin = new BufferedInputStream(fin);
                 OutputStream os = socket.getOutputStream()) {

                bin.read(bytearray, 0, bytearray.length);
                System.out.println("Sending file...");

                os.write(bytearray, 0, bytearray.length);
                os.flush();
                System.out.println("File transfer complete.");
            }

            socket.close();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
