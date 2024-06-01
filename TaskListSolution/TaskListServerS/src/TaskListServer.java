import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskListServer
{
   private TaskList tasks;
   private ServerSocket welcomeSocket;

   public TaskListServer(TaskList taskList, int port) throws IOException
   {
      this.tasks = taskList;
      this.welcomeSocket = new ServerSocket(port);
      System.out.println(
            "Starting Server " + InetAddress.getLocalHost().getHostAddress()
                  + " at port " + port + "...");
      execute();
   }

   private void execute()
   {
      while (true)
      {
         System.out.println("Waiting for a client...");
         try
         {
            Socket socket = welcomeSocket.accept();
            TaskListCommunicationThreadHandler c = new TaskListCommunicationThreadHandler(
                  socket, tasks);
            Thread t = new Thread(c);
            t.start();
         }
         catch (IOException e)
         {
            System.out.println("Error in server. Message: " + e.getMessage());
         }
      }
   }
}
