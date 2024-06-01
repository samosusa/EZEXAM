package mediator;

import com.google.gson.Gson;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class UpperCaseClient implements ServerModel{

    private static final String HOST = "localhost";
    private static final int PORT = 6789;
    private String host;
    private int port;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;
    private String receivedText;
    private PropertyChangeSupport property;
    public UpperCaseClient(String host, int port){
        this.port = port;
        this.host = host;
        property = new PropertyChangeSupport(this);

    }
    public UpperCaseClient() {
        this.port = PORT;
        this.host = HOST;
        property = new PropertyChangeSupport(this);
    }

    @Override
    public void connect() {
        try {
            Socket socket = new Socket(host,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            gson = new Gson();
            UppercaseClientReceiver receiver = new UppercaseClientReceiver(this,in);
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
    public synchronized String convert(String s) {
        NetworkPackage sendPackage = new NetworkPackage("Convert", s);
        String json = gson.toJson(sendPackage);
        out.println(json);
        System.out.println("sending " + sendPackage);
        try {
            wait(); //  wait until, someone notifies
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return receivedText;
    }

    public void receive(NetworkPackage s){
        if("Convert".equals(s.getType())){
            receivedText = s.getContent();
            notify();   //  notify all other threads
        }
        else{
            property.firePropertyChange(s.getType(), null, s.getContent());
        }
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
