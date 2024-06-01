package mediator;

import com.google.gson.Gson;
import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserClientHandler implements Runnable{

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;
    private String clientIp;
    private Model model;

    public UserClientHandler(Socket socket, Model model) throws IOException {
        this.socket = socket;
        this.model = model;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);

        clientIp = socket.getInetAddress().getHostAddress();

        running = true;
        gson = new Gson();
    }


    @Override
    public void run() {

        while (running){
            try {

                String data = in.readLine();                                        //  Reading data

                UserPackage userPackage = gson.fromJson(data, UserPackage.class);   //  Converting to origin object (names of classes can differ I guess)

                try{
                    model.addUser(userPackage.getUser(),userPackage.getPassword()); //  Using the object
                    System.out.println("???");
                }
                catch (IllegalArgumentException e){
                    out.println(e.getMessage());
                }

                out.println("You are connected with " + userPackage.getUser());



                running = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Server finished");
    }
}
