import javafx.application.Application;
import javafx.stage.Stage;
import model.TemperatureModel;
import model.TemperatureModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    // Model
    TemperatureModel model = new TemperatureModelManager();

    //  ViewModel
    ViewModelFactory factory = new ViewModelFactory(model);

    // View
    ViewHandler view = new ViewHandler(factory);
    view.start(primaryStage);
  }
}
