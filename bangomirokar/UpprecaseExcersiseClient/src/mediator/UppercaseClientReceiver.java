package mediator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;

public class UppercaseClientReceiver implements Runnable{

    private UpperCaseClient client;
    private BufferedReader in;
    private Gson gson;
    public UppercaseClientReceiver(UpperCaseClient client, BufferedReader in){
        this.client = client;
        this.in = in;   //  Access to the Buffered reader that has been handed down
        this.gson = new Gson();
    }

    @Override
    public void run() {
        while (true){
            try {
                String json = in.readLine();
                NetworkPackage receivedPackage = gson.fromJson(json, NetworkPackage.class);
                client.receive(receivedPackage);

            } catch (IOException e) {throw new RuntimeException(e);}
        }
    }
}
