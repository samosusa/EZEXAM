package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import view.LogViewController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogViewModel implements PropertyChangeListener {

    private ObservableList<String> logs;
    private Model model;
    private StringProperty textProperty;

    public LogViewModel(Model model){
        this.model = model;
        logs = FXCollections.observableArrayList();

        textProperty = new SimpleStringProperty();


        model.addListener(this);

    }

    public ObservableList<String> getLogs(){return logs;}
    public StringProperty getTextProperty(){
        return textProperty;
    }

    public void enterPressed(){
        model.addMessage(textProperty.get());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater (() -> {  //  Makes it run on separate thread therefore independent of the current view showing
            if("log".equals(evt.getPropertyName()))
                logs.add((String) evt.getNewValue());
            else if ("broadcast".equals(evt.getPropertyName())) {
                logs.add((String) evt.getNewValue());
            }
        });
    }
}
