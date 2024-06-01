import java.io.IOException;

public class BroadcastClient
{
   public static void main(String[] args) throws IOException
   {
      TaskListClient client = new TaskListClient("localhost", 2910);
   }
}