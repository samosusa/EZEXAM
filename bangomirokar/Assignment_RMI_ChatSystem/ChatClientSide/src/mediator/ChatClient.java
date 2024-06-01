package mediator;

import model.ChatModel;
import model.UserList;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

  public class ChatClient implements RemoteListener<String, String>, ChatModel
  {
    private ChatRemote server;
    private PropertyChangeSupport property;

    public ChatClient() throws IOException
    {
      start();
      property=new PropertyChangeSupport(this);
    }
    //establish server connection
    private void start()
    {
      try
      {
        UnicastRemoteObject.exportObject(this, 0);
        server=(ChatRemote) Naming.lookup("rmi://localhost:1099/Send");
        server.addListener(this, "Message");
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }

    }

    //send the entered data to the server and receive the reply
    @Override public String send(String username, String message)
    {
      try
      {
        return server.send(username, message);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
      return null;
    }

    //send the entered connection information to the server
    @Override public void createUser(String username, String password)
        throws IllegalArgumentException
    {
      try
      {
        server.createUser(username, password);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

    //prompt for the list of users from the server
    @Override public UserList getUsers()
    {
      try
      {
        return server.getUsers();
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
      return null;
    }

    @Override public void addListener(String s,
        PropertyChangeListener propertyChangeListener)
    {
      property.addPropertyChangeListener(s, propertyChangeListener);
    }

    @Override public void removeListener(String s,
        PropertyChangeListener propertyChangeListener)
    {
      property.removePropertyChangeListener(s, propertyChangeListener);
    }

    @Override public void propertyChange(
        ObserverEvent<String, String> observerEvent) throws RemoteException
    {
      //fire the message event further, for the model
      property.firePropertyChange(observerEvent.getPropertyName(), observerEvent.getValue1(), observerEvent.getValue2());
    }
  }

