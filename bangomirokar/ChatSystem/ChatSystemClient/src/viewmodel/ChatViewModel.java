package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ChatModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener
{
  private StringProperty headerProperty, inputProperty, errorProperty, listProperty;
  private ChatModel model;
  private ViewModelState viewModelState;



  private String test;

  public ChatViewModel(ChatModel model, ViewModelState state)
  {
    this.model=model;
    viewModelState=state;
    headerProperty=new SimpleStringProperty();
    inputProperty=new SimpleStringProperty();
    errorProperty=new SimpleStringProperty();
    listProperty=new SimpleStringProperty();



    model.addListener("Message", this);
    model.addListener("Command", this);
    model.addListener("broadcast", this);
  }

  public StringProperty getInputProperty()
  {
    return inputProperty;
  }
  public StringProperty getHeaderProperty()
  {
    return headerProperty;
  }
  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public StringProperty getListProperty()
  {
    return listProperty;
  }

  public void send()
  {
    try
    {
      //  send the user and their message, to update the conversation
      model.send(headerProperty.get(), inputProperty.get().trim());

      inputProperty.set("");  //  Just clearing the input field after pressing button
    }
    catch(Exception e)
    {
      errorProperty.set(e.getMessage());
    }
  }
  public void clear()
  {
    listProperty.set(null);
    inputProperty.set(null);
    errorProperty.set(null);
  }

  public void reset() {
    headerProperty.set(viewModelState.getUsername());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      // either message or command

      System.out.println(evt.getNewValue());

      listProperty.set(evt.getNewValue().toString());

      //  Getting the content, but cant put it inside a list?.. .wtf
      //  but maybe it should be calling model for conversation anyway
      //System.out.println(evt.getNewValue());
    });
  }
}
