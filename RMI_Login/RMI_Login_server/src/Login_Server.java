import mediator.RemoteModel;
import mediator.UserServer;
import model.Model;
import model.ModelManager;

import java.io.IOException;

public class Login_Server {
    public static void main(String[] args) throws IOException {
        RemoteModel server = new UserServer();
    }
}
