package model;


import mediator.ChatClient;
import mediator.ServerInterface;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;


//  Chat system <- model <- viewModel (for displaying after broadcast pops)
public class ChatModelManager implements ChatModel
{

  private PropertyChangeSupport property;

  private ChatClient server;

  public ChatModelManager() {

    property=new PropertyChangeSupport(this);

    try {
      server = new ChatClient("localhost");
      server.addListener("broadcast",this);
    } catch (RemoteException e) {
      System.out.println("Model Listener Part :C \n" + e.getMessage() );
    }

  }

  @Override public void send(String username, String message)
  {
    try {
      server.send(username,message);
    } catch (RemoteException e) {
      System.out.println("Model: " + e.getMessage());
    }
  }

  @Override public String getWholeConversation()
  {
    try {
      return server.getWholeConversation().getMessage();
    } catch (RemoteException e) {
      System.out.println("Model: " + e.getMessage());
      return null;
    }
  }

  @Override public void createUser(String username, String password) throws IllegalArgumentException
  {
    try {
      server.createUser(username,password);
    } catch (RemoteException e) {
      System.out.println("Model: " + e.getMessage());
    }
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName, listener);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    property.firePropertyChange("broadcast", null, evt.getNewValue());
  }
}
