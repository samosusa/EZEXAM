package mediator;

import model.UserList;
import utility.observer.listener.GeneralListener;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatRemote extends Remote
{
  String send(String username, String message) throws RemoteException; //used for sending the message, on Send button
  void createUser(String username, String password) throws RemoteException; // for user creation
  UserList getUsers() throws RemoteException;
  boolean addListener(GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException;
  boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException;
}
