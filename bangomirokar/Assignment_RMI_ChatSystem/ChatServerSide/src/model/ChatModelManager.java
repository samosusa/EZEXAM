package model;


import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChatModelManager implements ChatModel
{
  private UserList userList;
  private PropertyChangeSupport property;

  public ChatModelManager()
  {
    this.userList = new UserList();
    property=new PropertyChangeSupport(this);
  }
  @Override public String send(String username, String message)
  {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat(
        "dd/MM/yyyy HH:mm");
    if(message!=null)
    {
      if (!message.startsWith("/"))
      {
        //if we received a message, we fire an event
        String wholeMessage=sdf.format(date) + " " + username + ": " + '\n' + message + '\n';
        property.firePropertyChange("Message", username, wholeMessage);
        return wholeMessage;
      }
      switch(message)
      {
        case "/list":
          return sdf.format(date) + " " + username + ": " + '\n' + message + '\n' + getUsers() + '\n';
        case "/number":
          return sdf.format(date) + " " + username + ": " + '\n' + message + '\n' + getUsers().getSize() + '\n';
        case "/last":
          return sdf.format(date) + " " + username + ": " + '\n' + message + '\n' + getUsers().getLast() + '\n';
        default:
          throw new IllegalArgumentException("Invalid command");
      }
    }
    return null;
  }

  @Override public void createUser(String username, String password) throws IllegalArgumentException
  {
    //we try to create a user with the entered data
      User user=new User(username, password);
      userList.addUser(user);
  }
  @Override public UserList getUsers()
  {
    return userList;
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
