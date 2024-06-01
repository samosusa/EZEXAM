import utility.observer.subject.PropertyChangeHandler;

import java.io.IOException;

public class BroadcastServer
{
   public static void main(String[] args) throws IOException
   {
      PropertyChangeHandler<String, String> handler = new PropertyChangeHandler<>(null);

      TaskList taskList = new TaskList();
      TaskListServer server = new TaskListServer(taskList, 2910);


   }
}
