package mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteModel extends Remote {
    void addUser(String username, String password) throws RemoteException;

    String getUser(String username) throws RemoteException;
}
