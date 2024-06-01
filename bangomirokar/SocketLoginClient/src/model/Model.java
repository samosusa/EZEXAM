package model;

public interface Model
{
  public void login(String userName, String password)
      throws IllegalStateException, IllegalArgumentException;
}
