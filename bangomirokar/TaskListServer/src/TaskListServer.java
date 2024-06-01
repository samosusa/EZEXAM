import java.io.IOException;
import java.net.ServerSocket;

public class TaskListServer {
    private ServerSocket welcomeSocket;
    private TaskList tasks;
    public TaskListServer(TaskList tasks, int port) throws IOException {
        this.tasks = tasks;
        welcomeSocket = new ServerSocket(port);

        TaskListCommunicationThreadHandler handler = new TaskListCommunicationThreadHandler(welcomeSocket.accept(), tasks);
        tasks.addListener(handler);
    }
}
