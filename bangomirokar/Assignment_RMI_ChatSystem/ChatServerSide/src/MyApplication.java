import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatServer;
import model.ChatModel;
import model.ChatModelManager;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MyApplication extends Application {
  private ChatServer server;
  @Override public void start(Stage primaryStage)
  {
    ChatModel model = new ChatModelManager();
    try
    {
      server=new ChatServer(model);
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
}
