import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TaskListClient
{
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;
  private Scanner input;
  private String receivedString;

  public TaskListClient(String host, int port)
  {
    try
    {
      input = new Scanner(System.in);
      socket = new Socket(host, port);
      System.out.println(socket.hashCode());
      System.out
          .println("DEBUG: Connected to server: " + host + " at port " + port);

      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      Thread t = new Thread(new TaskListClientReceiver(this, in));
      t.setDaemon(true);
      t.start();
      execute();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  public synchronized void receive(String s)
  {
    if (s.equalsIgnoreCase("ADD"))
    {
      System.out.println("Server> A new task has been added");
    }
    else if (s.equalsIgnoreCase("REMOVE"))
    {
      System.out.println("Server> A task has been removed");
    }
    else
    {
      receivedString = s;
      notify();
    }
  }

  //  This will wait until receive string is populated
  private synchronized String waitLoop()
  {
    while (receivedString == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        //
      }
    }
    String line = receivedString;
    receivedString = null;
    return line;
  }

  private void execute() throws IOException
  {
    Gson gson = new Gson();
    boolean continueCommuticating = true;
    while (continueCommuticating)
    {
      System.out.println("1) Add a task");
      System.out.println("2) Get a task");
      System.out.println("3) Get task size");
      System.out.println("Any other number) Exit");
      int value = input.nextInt();
      input.nextLine();

      String line;
      switch (value)
      {
        case 1:
          System.out.print("Enter the task: ");
          String taskString = input.nextLine();
          System.out.print("Enter the estimated time: ");
          long time = input.nextLong();
          input.nextLine();
          Task task = new Task(taskString, time);
          String jSonString = gson.toJson(task);
          out.println("ADD");
          out.println(jSonString);
          break;
        case 2:
          out.println("GET");
          line = waitLoop();
          if (!line.equals("ERROR"))
          {
            task = gson.fromJson(line, Task.class);
            System.out.println("Server> " + task);
          }
          else
          {
            System.out.println("Server> " + line);
          }
          break;
        case 3:
          out.println("SIZE");
          line = waitLoop();
          System.out.println("Server> " + line);
          break;
        default:
          continueCommuticating = false;
          out.println("EXIT");
          line = waitLoop();
          System.out.println("Server> " + line);
          break;
      }
    }
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (Exception e)
    {
      //
    }
  }

}
