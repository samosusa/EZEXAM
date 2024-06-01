package mediator;

import model.ChatModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable{

    private ChatModel model;
    private boolean running;
    private ServerSocket welcomeSocket;
    private int port;
    private String address;
    public ChatServer(ChatModel model){
        this.model = model;
        port = 1234;
        address = "localhost";
    }

    @Override
    public void run() {
        //  Add logging
        try {
            welcomeSocket = new ServerSocket(port);

            running = true;
            while (running){
                Socket socket = welcomeSocket.accept(); //  welcome a client
                ChatClientHandler clientHandler = new ChatClientHandler(socket,model);
                Thread clientThread = new Thread(clientHandler);
                clientThread.setDaemon(true);
                clientThread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
