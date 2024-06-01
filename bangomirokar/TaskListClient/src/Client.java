import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        new TaskListClient("localhost",0420).execute();
    }
}
