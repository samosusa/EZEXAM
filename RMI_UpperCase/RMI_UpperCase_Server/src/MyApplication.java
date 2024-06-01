import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ServerUpperClass;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{


  @Override
  public void start(Stage stage)  {
    Model model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);

    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(stage);

    // starting server
    new ServerUpperClass(model);
  }
}