import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;

public class TaskListClientReceiver implements Runnable
{
  private TaskListClient taskListClient;
  private BufferedReader in;

  public TaskListClientReceiver(TaskListClient taskListClient,
      BufferedReader in)
  {
    this.in = in;
    this.taskListClient = taskListClient;
  }

  @Override public void run()
  {
    while (true)
    {
      try
      {
        String line = in.readLine();
        System.out.println("RECEIVER: " + line + " on " + in);
        taskListClient.receive(line);
      }
      catch (IOException e)
      {
        //
      }
    }
  }
}
