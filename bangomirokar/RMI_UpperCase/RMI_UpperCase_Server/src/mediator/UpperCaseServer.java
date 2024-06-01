package mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpperCaseServer extends Remote {

    String convert(String text) throws RemoteException;
}
