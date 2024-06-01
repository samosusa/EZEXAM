import mediator.UserServer;
import model.Model;
import model.ModelManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Model model = new ModelManager();

        UserServer server = new UserServer(model);
        Thread tr = new Thread(server);
        tr.start();
    }
}
