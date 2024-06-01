import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerToClient {
    public static void main(String[] args) throws IOException {
        final int PORT = 2910;
        //  SERVER ADD: 10.154.212.81

        ServerSocket welcomeSocket = new ServerSocket(PORT);

        boolean connection = true;
        while(connection){
            System.out.println("Waiting for client...");

            Socket socket = welcomeSocket.accept();

            //  Create input stream attached to socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //  Create output stream attached to the socket
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            String request = in.readLine(); //  Read line from client           SEND

            String client = socket.getInetAddress().getHostAddress();   //  IP of connected client

            if(request.equals("connect")){
                System.out.println("Connected on server; " + client);
                out.println("Connected");                               //  RECIEVE
            }
            else{
                System.out.println("Wrong hello message for " + client);
                out.println("Disconnected");
                connection = false;
                continue;
            }



            while (true){
                out.println("What is on your mind??");   //                                   Recuve
                String clientMsg = in.readLine();
                System.out.println(clientMsg);
                if(clientMsg.equals("exit")){
                    connection = false;
                    out.println("exit");
                    socket.close();
                    break;
                }
                else{
                    out.println(clientMsg + " is on your mind");
                }
            }


//            String msg = in.readLine(); //                                      send
//            System.out.println("Username = " + msg);
//
//            out.println("Password?");   //                                   Recuve
//            String pswd = in.readLine();
//            System.out.println("Password = " + pswd);
//
//            out.println("Approved for client " + client + ", message: " + msg);   // Recieve




        }
    }
}
