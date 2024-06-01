package mediator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReceiver implements Runnable{

    private ChatClient client;
    private BufferedReader in;
    private Gson gson;

    public ChatClientReceiver(ChatClient client, BufferedReader in){
        this.client = client;
        this.in = in;
        this.gson = new Gson();
    }

    @Override
    public void run() {
        while (true){
            try {
                String incomingConvo = in.readLine();

                client.receive(incomingConvo);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
