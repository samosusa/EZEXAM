import java.io.BufferedReader;
import java.io.IOException;

public class TaskListClientReceiver implements Runnable{
    private BufferedReader in;
    private TaskListClient client;
    public TaskListClientReceiver(TaskListClient client, BufferedReader in){
        this.in = in;

        this.client = client;
    }
    @Override
    public void run() {

        while(true){
            try {
                //  Printout from server PropertyChange?

                String r = in.readLine();
                System.out.println("Run: " + r);
                client.receive(r);

            } catch (IOException | InterruptedException e) {throw new RuntimeException(e);}
        }
    }
}
