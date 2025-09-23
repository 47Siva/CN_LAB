import java.io.*;
import java.net.*;

class TcpchatClient {
    public static void main(String args[]) throws Exception {
        Socket Clt;
        PrintWriter toServer;
        BufferedReader fromServer, fromUser;

        try {
            Clt = new Socket(InetAddress.getLocalHost(), 4000);
            toServer = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(Clt.getOutputStream())), true);
            fromServer = new BufferedReader(
                        new InputStreamReader(Clt.getInputStream()));
            fromUser = new BufferedReader(
                        new InputStreamReader(System.in));

            String CltMsg, SrvMsg;
            System.out.println("Type \"end\" to quit");

            while (true) {
                System.out.print("Message to Server: ");
                CltMsg = fromUser.readLine();
                toServer.println(CltMsg);

                if (CltMsg.equals("end"))
                    break;

                SrvMsg = fromServer.readLine();
                System.out.println("Client: " + SrvMsg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}