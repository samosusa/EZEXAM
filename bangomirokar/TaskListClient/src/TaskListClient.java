import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TaskListClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner input;
    private Gson gson;

    //

    private String receiveString;

    public TaskListClient(String host, int port) throws IOException {
        socket = new Socket(host,port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        gson = new Gson();

        input = new Scanner(System.in);

        //  Receiver waiting for a broadcast?
        TaskListClientReceiver clientReceiver = new TaskListClientReceiver(this,in);
        Thread t = new Thread(clientReceiver);
        t.start();
    }

    public synchronized void receive(String s) throws InterruptedException {
        receiveString = null;

        while (s.equals("ADD") || s.equals("REMOVE")){
            System.out.println("recieved");
            wait();
        }
        receiveString = s;
        notifyAll();

        System.out.println("Broadcast from server> " + receiveString);
    }
    public void execute() throws IOException, InterruptedException {
        boolean run = true;
        while(run){
            System.out.println("Choose option ( ADD, GET, SIZE )");
            String toSend = input.nextLine();   //  "ADD","GET","SIZE",""
            out.println(toSend);                //  Sending the chosen option

            switch (toSend){
                case "ADD" -> {
                    System.out.println("Enter Task");
                    String nameOfTask = input.nextLine();

                    input.nextLine();

                    System.out.println("Enter time");
                    long time = input.nextLong();

                    Task task = new Task(nameOfTask, time);
                    String json = gson.toJson(task);
                    out.println(json);

                }
                case "GET" -> {
                    String jsonObject = in.readLine();
                    if(!jsonObject.equals("error")){
                        //Task fromString = gson.fromJson(jsonObject, Task.class);
                        //System.out.println(fromString);
                        System.out.println("FromJson bullshit");
                    }
                    else{
                        System.out.println("Empty list blud");
                    }
                }
                case "SIZE" -> {
                    System.out.println(in.readLine());
                }
                case "EXIT" -> {    // default case bug, "EXIT" used instead
                    System.out.println(in.readLine());
                    System.out.println("Ending the connection");
                    run = false;
                }
            }
        }
    }
}
