import java.io.*;
import java.net.*;

class TcpchatServer {
    public static void main(String args[]) throws Exception {
        PrintWriter toClient;
        BufferedReader fromUser, fromClient;

        try {
            ServerSocket srv = new ServerSocket(4000);
            System.out.print("\nServer started\n");

            Socket Clt = srv.accept();
            System.out.print("Client connected");

            toClient = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(Clt.getOutputStream())), true);
            fromClient = new BufferedReader(
                        new InputStreamReader(Clt.getInputStream()));
            fromUser = new BufferedReader(
                        new InputStreamReader(System.in));

            String CltMsg, SrvMsg;

            while (true) {
                CltMsg = fromClient.readLine();
                if (CltMsg.equals("end"))
                    break;
                else {
                    System.out.println("Server received: " + CltMsg);
                    System.out.print("Message to Client: ");
                    SrvMsg = fromUser.readLine();
                    toClient.println(SrvMsg);
                }
            }

            System.out.println("\nClient Disconnected");
            fromClient.close();
            toClient.close();
            fromUser.close();
            Clt.close();
            srv.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}