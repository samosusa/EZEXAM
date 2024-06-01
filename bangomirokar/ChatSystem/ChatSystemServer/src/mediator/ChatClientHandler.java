package mediator;

import com.google.gson.Gson;
import javafx.application.Platform;
import model.*;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientHandler implements Runnable, PropertyChangeListener {


    //  Add listener to model change for broadcast... the list
    private ChatModel model;
    private boolean running;


    //  Server variables:
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;
    public ChatClientHandler(Socket socket, ChatModel model) throws IOException {
        this.model = model;
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        gson = new Gson();

        //  this class should listen to model bruv
        //  and broadcast the model change for all clients...
        model.addListener("broadcast",this);
    }



    @Override
    public void run() {
        running = true;
        while (running){

            //  Here you read from client thread. then handle the responses.
            //  read json strings and convert them into package based on ... idk
            //  feed the input from client to actual model
            //  IDEA: first read just one word string then json object.

            try {

                String clientInputType, clientInputContent;
                clientInputType = in.readLine();
                clientInputContent = in.readLine();

                //  type: Message, Command
                //  TODO: case for quit : close()
                switch (clientInputType){
                    case "Message":
                        MessagePackage message = gson.fromJson(clientInputContent, MessagePackage.class);
                        model.send(message.getSender(), message.getTextContent());
                        break;
                    case "Command":
                        CommandPackage command = gson.fromJson(clientInputContent, CommandPackage.class);
                        model.send(command.getSender(),command.getCommand());
                }


            } catch (IOException e) {
                close();
            }

        }
    }

    public void close(){
        running = false;
        try{
            in.close();
            out.close();
            socket.close();
        } catch (IOException e){}
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //  This is fired when message from client is received....
        //  This then fires and sends to all client threads
        Platform.runLater(() -> {
            out.println(evt.getNewValue());
        });
    }
}
