import mediator.UserClient;
import model.Model;
import model.ModelManager;

import java.io.IOException;
import java.util.Scanner;

public class Login_Client {
    public static void main(String[] args) throws IOException {

        UserClient client = new UserClient();
        Model model = new ModelManager(client);

        // Both work!
        model.login("Matko", "Patko123");
        System.out.println(model.getUser("Matko"));

    }
}