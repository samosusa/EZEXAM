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
    //  Think about handling this in lower classes
    PackageCreator creator;
    Package sendMessage=null, sendCommand=null;


    if(!message.startsWith("/"))
    {
      creator=new MessagePackageCreator();
      sendMessage=creator.createPackage(username, message, "");
    }
    else
    {
      creator=new CommandPackageCreator();
      sendCommand=creator.createPackage(username, message, userList);
    }



    if(sendMessage!=null) {
      conversation.addPackage(sendMessage);
      property.firePropertyChange("Message", null, message);
    }
    else {
      conversation.addPackage(sendCommand);
      property.firePropertyChange("Command", null, message);
    }
  }

  @Override public String getWholeConversation()
  {
      //    Broadcasts everytime a chat is reset in the viewň
      //    meaning: somebody sends a message
    property.firePropertyChange("broadcast", null, conversation.getConversationContent());  //  maybe
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
