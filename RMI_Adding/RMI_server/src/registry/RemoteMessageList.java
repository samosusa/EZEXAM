package registry;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMessageList extends Remote {
    void addMessage(String message, RemoteSender sender) throws RemoteException;
}
