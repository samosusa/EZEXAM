package viewmodel;

import external.RunnableClock;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TempViewModel implements PropertyChangeListener {

    // TextInput, laberTimer, labelOutput


    private TemperatureModel model;
    private StringProperty textInput;
    private StringProperty labelTimer;
    private StringProperty labelOutput;
    public TempViewModel(TemperatureModel model){
        this.model = model;
        textInput = new SimpleStringProperty();
        labelTimer = new SimpleStringProperty();
        labelOutput = new SimpleStringProperty();

        Platform.runLater(() -> {
            RunnableClock clock = new RunnableClock();

            clock.addListener(this);

            Thread tr = new Thread(clock, "thread");

            tr.setDaemon(true);

            tr.start();
        });
    }

    public void toCelsius(){
        try{
            double input = Double.parseDouble(textInput.get());
            double output = model.toCelsius(input);
            labelOutput.set(Double.toString(output));
        }
        catch (Exception e){ labelOutput.set("Error mf");}
    }

    public void toFahrenheit(){

        try{
            double input = Double.parseDouble(textInput.get());
            double output = model.toFahrenheit(input);
            labelOutput.set(Double.toString(output));
        }
        catch (Exception e) { labelOutput.set("Error mfaf");}

    }

    public StringProperty textInputProperty() {
        return textInput;
    }
    public StringProperty labelTimerProperty() {
        return labelTimer;
    }

    public StringProperty labelOutputProperty() {
        return labelOutput;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) { //  Property is being fired every second
        Platform.runLater(() -> labelTimer.set(evt.getNewValue().toString()));  //  Run later for threads to be able to function as well as JavaFx
    }
}
