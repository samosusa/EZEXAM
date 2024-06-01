import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatServer;
import model.ChatModel;
import model.ChatModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ChatModel model = new ChatModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);

        view.start(stage);

        ChatServer server = new ChatServer(model);
    }
}