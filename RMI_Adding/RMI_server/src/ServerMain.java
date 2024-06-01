import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args) throws MalformedURLException, RemoteException {

        startRegistry();

        RmiServer server = new RmiServer();
        server.start();
        System.out.println("Starting server");
    }
    public static void startRegistry() throws RemoteException
    {
        try{
            Registry reg = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started...");
        } catch (java.rmi.server.ExportException e){
            System.out.println("Registry already started? ; " + e.getMessage());
        }
    }
}