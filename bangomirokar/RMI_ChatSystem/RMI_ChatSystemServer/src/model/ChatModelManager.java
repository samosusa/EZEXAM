package model;


import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel, NamedPropertyChangeSubject
{
  private Conversation conversation;
  private UserList userList;
  private PropertyChangeSupport property;

  public ChatModelManager() {
    this.userList = new UserList();
    conversation = new Conversation();
    property=new PropertyChangeSupport(this);
  }
  @Override public void send(String username, String message)
  {
    conversation.addPackage(username + ": " + message);
    //  For displaying to view on server
    System.out.println(username + ": " + message);

    //  This does not fire for some reason... or the listeners are wrong :P
    property.firePropertyChange("NewMessage",null, username + ": " + message);
  }

  @Override public String getWholeConversation()
  {
    return conversation.getConversationContent();
  }

  @Override public void createUser(String username, String password) throws IllegalArgumentException
  {
    userList.addUser(username, password);
  }


  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName, listener);
  }
}
