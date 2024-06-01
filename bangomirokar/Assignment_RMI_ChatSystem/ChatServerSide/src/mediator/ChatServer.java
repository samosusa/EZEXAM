package mediator;

import model.ChatModel;
import model.UserList;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;
import utility.observer.subject.RemoteSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer implements ChatRemote, RemoteSubject<String, String>, PropertyChangeListener
{
  private ChatModel model;
  private PropertyChangeHandler<String, String> property;

  public ChatServer(ChatModel model) throws MalformedURLException, RemoteException
  {
    this.model=model;
    property=new PropertyChangeHandler<>(this, true);
    model.addListener("Message", this);
    startRegistry();
    startServer();
  }

  private void startRegistry()
  {
    try
    {
      Registry reg= LocateRegistry.createRegistry(1099);
      //System.out.println("Registry started...");
    }
    catch(RemoteException e)
    {
      e.printStackTrace();
    }
  }
  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Send", this);
  }

  //delegate the work to the model
  @Override public String send(String username, String message) throws RemoteException
  {

    return model.send(username, message);
  }

  @Override public void createUser(String username, String password) throws RemoteException
  {
    model.createUser(username, password);
  }

  @Override public UserList getUsers() throws RemoteException
  {
    return model.getUsers();
  }
  @Override public boolean addListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }


  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    property.firePropertyChange(evt.getPropertyName(),
        (String) evt.getOldValue(), (String) evt.getNewValue());
  }

}
