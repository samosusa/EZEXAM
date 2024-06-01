import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatClient;
import model.ChatModel;
import model.ChatModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application {
  @Override public void start(Stage primaryStage) throws Exception {
    try
    {
      ChatModel model = new ChatModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);
      ChatClient client = new ChatClient();
      view.start(primaryStage);
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
