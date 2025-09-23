import java.io.*;
import java.net.*;

public class FTClient {
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 4000);
            System.out.println("Connected to server.");

            // Receive file from server
            byte[] bytearray = new byte[1024 * 1024]; // 1MB buffer
            InputStream is = socket.getInputStream();

            FileOutputStream fos = new FileOutputStream("received.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int bytesRead;
            while ((bytesRead = is.read(bytearray, 0, bytearray.length)) != -1) {
                bos.write(bytearray, 0, bytesRead);
            }

            bos.close();
            socket.close();
            System.out.println("File received successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
