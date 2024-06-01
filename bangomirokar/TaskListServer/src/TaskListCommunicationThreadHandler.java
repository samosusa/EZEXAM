import com.google.gson.Gson;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TaskListCommunicationThreadHandler implements Runnable, PropertyChangeListener {
    private BufferedReader in;
    private PrintWriter out;
    private String ip;
    private TaskList tasks;

    //  For Json
    private Gson gson;
    public TaskListCommunicationThreadHandler(Socket socket, TaskList tasks) throws IOException {

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));   //  Read note
        out = new PrintWriter(socket.getOutputStream(), true);
        ip = socket.getInetAddress().getHostAddress();

        gson = new Gson();

        this.tasks = tasks;


        //  Starting the thread on creation
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        //  Implement options "ADD", "GET" and "SIZE" .
        //  Any other string exits the interaction
        //  Send data through json
        boolean run = true;
        while(run){
            try {
                System.out.println("Waiting for client to choose");
                String option = in.readLine();
                System.out.println(option);
                switch (option){
                    case "ADD" -> {
                        //  Getting data
                        String data = in.readLine();                    // bcuz we reading from json string
                        Task task = gson.fromJson(data, Task.class);
                        tasks.addTask(task);

                    }
                    case "GET" -> {
                        String sendingString = "";
                        Task task = tasks.getAndRemoveNextTask();

                        if (task != null) {
                            sendingString = gson.toJson(task);
                        }
                        else{
                            sendingString = "error";
                        }
                        out.println(sendingString);

                    }
                    case "SIZE" -> {
                        int size = tasks.size();
                        out.println("Size is" + size);
                    }
                    case "EXIT" -> {    //  default: did not work for me
                        System.out.println("server> exiting");
                        out.println("EXIT");
                        run = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Exception?");
                break;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property change: " + evt.getPropertyName());
        out.println(evt.getPropertyName());
    }
}
