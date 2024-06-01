import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {

        int PORT = 0420;

        TaskList taskList = new TaskList();
//        taskList.addTask(new Task("SADSAd", 1));
//        taskList.addTask(new Task("REWWE", 232));
//        taskList.addTask(new Task("sdxyxxyc", 535));
//        taskList.addTask(new Task("SD", 2));


        new TaskListServer(taskList, PORT);
    }
}
