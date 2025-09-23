import java.io.*;
import java.net.*;

class Client {
    public static void main(String args[]) {
        Socket sock = null;
        PrintWriter writer = null;

        System.out.println("Trying to connect");

        try {
            sock = new Socket(InetAddress.getLocalHost(), Server.PORT);

            // Send message to server
            writer = new PrintWriter(sock.getOutputStream(), true);
            writer.println("Hi from client");

            // Read response from server (FIXED)
            BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            System.out.println(reader.readLine());

        } catch (SocketException e) {
            System.out.println("SocketException " + e);
        } catch (IOException e) {
            System.out.println("IOException " + e);
        } finally {
            try {
                if (sock != null) sock.close();
            } catch (IOException ie) {
                System.out.println("Close Error :" + ie.getMessage());
            }
        }
    }
}
