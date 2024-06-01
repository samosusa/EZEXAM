package mediator;

import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UserServer implements Runnable{

    private static final int PORT = 2910;
    private boolean running;
    private ServerSocket welcomeSocket;
    private Model model;

    public UserServer(Model model) throws IOException {
        this.model = model;
        welcomeSocket = new ServerSocket(PORT);
        running = true;

        Thread tr = new Thread(this);
        tr.start();
    }

    @Override
    public void run() {
        while (running){
            System.out.println("Waiting for client....");

            Socket socket = null;
            try {
                socket = welcomeSocket.accept();
                System.out.println("Connection accepted from: " + socket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                UserClientHandler clientHandler = new UserClientHandler(socket,model);
                Thread tr = new Thread(clientHandler);
                tr.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
