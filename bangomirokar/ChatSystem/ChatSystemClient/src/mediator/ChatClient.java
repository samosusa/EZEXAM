package mediator;

import com.google.gson.Gson;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient implements ServerModel, NamedPropertyChangeSubject {


    private String address;
    private int port;

    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;

    String receivedText;
    private PropertyChangeSupport property;

    public ChatClient(String address, int port){
        this.address = address;
        this.port = port;
        this.property = new PropertyChangeSupport(this);
    }
    public ChatClient(){
        this("localhost",1234);
    }

    @Override
    public void connect() {
        try {
            Socket socket = new Socket(address,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            gson = new Gson();

            ChatClientReceiver receiver = new ChatClientReceiver(this,in);
            Thread thread = new Thread(receiver);
            thread.setDaemon(true);
            thread.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disconnect() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(String username, String message) {


        if(!message.startsWith("/"))
        {
            MessagePackage sendMessage = new MessagePackage(username,message, null);
            String messageToJson = gson.toJson(sendMessage);

            out.println("Message");
            out.println(messageToJson);
        }
        else
        {
            CommandPackage sendCommand = new CommandPackage(username,message,null);
            String commandToJson = gson.toJson(sendCommand);

            out.println("Command");
            out.println(commandToJson);
        }

    }

    public void receive(String c){


        //  Use for commands I guess
        receivedText = c;

        property.firePropertyChange("broadcast", null, c);

    }
    @Override
    public String getWholeConversation() {

        return receivedText;
    }

    @Override
    public void createUser(String username, String password) throws IllegalArgumentException {

    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(propertyName,listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        property.removePropertyChangeListener(propertyName,listener);
    }
}
