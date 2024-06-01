import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiTaskClient{
    private RemoteTaskList server;

    private String host;
    public RmiTaskClient(String host){
        this.host = host;
    }
    public void start() throws RemoteException {
        String nameForConnection = "Case";
        String fullString = "rmi://" + host + ":1099/" + nameForConnection;
        try {
            //  getting the server interface from registry. "localhost" is placeholder of IP
            //  1099 stays usually the same, "Something" is the one you named!
            server = (RemoteTaskList) Naming.lookup("rmi://localhost:1099/Case");
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        //  Test shit
        server.add(new Task("SDJ",123));
        server.add(new Task("DMA",321));
        System.out.println(server.get());
        System.out.println(server.size());

        //  Back to server?
        //UnicastRemoteObject.exportObject(this,0);
    }

}
