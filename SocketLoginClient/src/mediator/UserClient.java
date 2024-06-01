package mediator;

import com.google.gson.Gson;
import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserClient implements Model {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;

    public UserClient(String host, int port) throws IOException {
        socket = new Socket(host,port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        gson = new Gson();  //  Creating
    }


    @Override
    public void login(String userName, String password) throws IllegalStateException, IllegalArgumentException {
        User user = new User(userName,password);    //  The object
        String json = gson.toJson(user);            //  converting to json
        out.println(json);                          //  sending data

        try {
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
