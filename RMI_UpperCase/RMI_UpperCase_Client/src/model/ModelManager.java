package model;

import mediator.UpperCaseClient;

import java.rmi.RemoteException;

public class ModelManager implements Model
{

  private UpperCaseClient server;
  public ModelManager()
  {
    server = new UpperCaseClient("localhost");
  }

  @Override
  public String convert(String source)
  {
    try {
      return server.convert(source);
    } catch (RemoteException e) {
      System.out.println("FROM model: " + e.getMessage());
      return null;
    }
  }


}
