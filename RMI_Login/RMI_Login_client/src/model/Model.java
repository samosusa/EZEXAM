package model;

import java.rmi.RemoteException;

public interface Model
{
  public void login(String userName, String password)
          throws IllegalStateException, IllegalArgumentException, RemoteException;
  String getUser(String username) throws RemoteException;
}
