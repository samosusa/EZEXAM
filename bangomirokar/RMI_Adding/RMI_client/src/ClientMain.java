import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Scanner input = new Scanner(System.in);

        RmiClient client = new RmiClient();


        while(true){
            System.out.println("Enter a message for server: ");
            String line = input.nextLine();
            client.send(line);
        }
    }
}