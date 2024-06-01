package model;

import mediator.ServerModel;
import mediator.UpperCaseClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model, PropertyChangeListener
{
private ServerModel serverModel;
  private PropertyChangeSupport property;

  public ModelManager()
  {
    this.serverModel = new UpperCaseClient("localhost",6789);
    this.property = new PropertyChangeSupport(this);


    //  This is for broadcast
    serverModel.addListener("Error",this);
    serverModel.addListener("Message",this);
  }

  @Override
  public String convert(String source)
  {
    String reply = serverModel.convert(source);
    property.firePropertyChange("Convert",null, reply);
    return reply;
  }
  


  @Override
  public void propertyChange(PropertyChangeEvent evt) {

  }

  @Override
  public void addListener(String propertyName, PropertyChangeListener listener) {
    property.addPropertyChangeListener(propertyName,listener);
  }

  @Override
  public void removeListener(String propertyName, PropertyChangeListener listener) {
    property.removePropertyChangeListener(propertyName,listener);
  }
}
