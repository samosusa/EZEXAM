import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClentToServer {
    public static void main(String[] args) throws IOException {

        final int PORT = 6789;
        final int PORT_2 = 5678;
        final int PORT_3 = 2910;
        final String HOST = "10.154.206.94";    //  IP ADDRESS
        final String HOST_2 = "10.154.214.64";    //  IP ADDRESS
        final String HOST_3 = "10.154.212.97";    //  IP ADDRESS
        final String L_HOST = "localhost";    //  IP ADDRESS

        Scanner input = new Scanner(System.in);

        Socket socket = new Socket(L_HOST,PORT_3);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);



        String request = "connect";   //  Server requires for client to first say hello
        out.println(request);         // This is where the message is sent

        //  Line from server
        String reply = in.readLine();
        System.out.println(reply);  // just a print out to console

        System.out.println(in.readLine());

        //  Message for server
        String message = input.nextLine();
        out.println(message);

        System.out.println(in.readLine());


        socket.close();

//        for (int i = 0; i < 100; i++) {
//
//            //  Line from server
//            String reply = in.readLine();
//            System.out.println("Server> " + reply);
//
//            //  Message for server
//            String message = input.nextLine();
//            out.println(message);
//        }









    }
}