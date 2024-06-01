import com.google.gson.Gson;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.Socket;

public class TaskListCommunicationThreadHandler
    implements Runnable, PropertyChangeListener
{
  private BufferedReader in;
  private PrintWriter out;
  private String ip;
  private TaskList tasks;

  public TaskListCommunicationThreadHandler(Socket socket, TaskList tasks)
  {
    this.tasks = tasks;
    this.tasks.addListener(this);
    try
    {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      this.ip = socket.getInetAddress().getHostAddress();
      System.out.println(ip + " connected");
    }
    catch (IOException e)
    {
      System.out
          .println("Error for client connection. Message: " + e.getMessage());
    }
  }

  @Override public void run()
  {
    Gson gson = new Gson();
    boolean continueCommunicating = true;
    try
    {
      while (continueCommunicating)
      {
        String type = in.readLine();
        System.out.println(ip + "> " + type);
        Task task;

        switch (type)
        {
          case "ADD":
            String taskString = in.readLine();
            task = gson.fromJson(taskString, Task.class);
            tasks.add(task);
            break;
          case "GET":
            task = tasks.getAndRemoveNextTask();
            if (task != null)
            {
              String jSonString = gson.toJson(task);
              out.println(jSonString);
            }
            else
            {
              out.println("ERROR");
            }
            break;
          case "SIZE":
            out.println("" + tasks.size());
            break;
          default:
            continueCommunicating = false;
            out.println("EXIT");
            break;
        }
      }
    }
    catch (Exception e)
    {
      String message = e.getMessage();
      if (message == null)
      {
        message = "Connection lost";
      }
      System.out.println("Error for client: " + ip + " - Message: " + message);
    }
    System.out.println("Closing connection to client: " + ip);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    System.out.println("Server to clients> " + evt.getPropertyName());
    out.println(evt.getPropertyName());
  }
}
