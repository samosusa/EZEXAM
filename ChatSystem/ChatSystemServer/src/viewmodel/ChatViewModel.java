package viewmodel;

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

  //initializations
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
  }
  public void setHeaderProperty(String headerProperty) {
    this.headerProperty.set(headerProperty);
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
      // either message or command
      listProperty.set(model.getWholeConversation());
  }
}
