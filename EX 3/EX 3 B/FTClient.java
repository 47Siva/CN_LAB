import java.io.*;
import java.net.*;

public class FTClient {
    public static void main(String[] args) {
        final int PORT = 4000;

        try (Socket socket = new Socket(InetAddress.getLocalHost(), PORT)) {
            System.out.println("Connected to server.");

            // Save received file as "received.txt"
            try (InputStream is = socket.getInputStream();
                 FileOutputStream fos = new FileOutputStream("received.txt");
                 BufferedOutputStream bos = new BufferedOutputStream(fos)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
                System.out.println("File received successfully as received.txt");
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
