package mediator;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UserClient implements RemoteModel{

    RemoteModel server;

    public UserClient() throws RemoteException {
        try {
            //  getting the server interface from registry. "localhost" is placeholder of IP
            //  1099 stays usually the same, "Something" is the one you named!
            server = (RemoteModel) Naming.lookup("rmi://localhost:1099/Login");

        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(String username, String password) throws RemoteException {
        server.addUser(username,password);
    }

    @Override
    public String getUser(String username) throws RemoteException {
        return server.getUser(username);
    }
}
